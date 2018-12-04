package com.sdk.wifi.util.aws.firehose;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClient;
import com.amazonaws.services.kinesisfirehose.model.DeliveryStreamDescription;
import com.amazonaws.services.kinesisfirehose.model.DescribeDeliveryStreamRequest;
import com.amazonaws.services.kinesisfirehose.model.DescribeDeliveryStreamResult;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchResult;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;
import com.amazonaws.services.kinesisfirehose.model.S3DestinationUpdate;
import com.amazonaws.services.kinesisfirehose.model.UpdateDestinationRequest;

public class FireHoseUtil {

    private static final Log LOGGER = LogFactory.getLog(FireHoseUtil.class);

    // DeliveryStream properties
    private static AmazonKinesisFirehoseClient firehoseClient;
    private static final String FIRE_HOSE_REGION = "us-west-2";

    /**
     * 批量添加数据到流里面
     */
    public static void addBatchFireHose(List<Record> records,
            String deliveryStreamName) {
        try {
            putRecordBatch(records, deliveryStreamName);
        } catch (Exception e) {
            LOGGER.error("写流错误" + e);
        }
    }

    /**
     * 
     * 单个写流
     */
    public static void addFireHose(Record record, String deliveryStreamName) {
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setDeliveryStreamName(deliveryStreamName);
        putRecordRequest.setRecord(record);
        firehoseClient.putRecord(putRecordRequest);
    }

    /**
     * 字符串边record
     * 
     * @param data
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static Record createRecord(String data) throws UnsupportedEncodingException {
        return new Record().withData(ByteBuffer.wrap(data.getBytes("UTF-8")));
    }

    /**
     * 初始化流
     */
    public static void initClients() {
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. "
                            + "Please make sure that your credentials file is at the correct "
                            + "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        // Firehose client
        firehoseClient = new AmazonKinesisFirehoseClient(credentials);
        firehoseClient.setRegion(RegionUtils.getRegion(FIRE_HOSE_REGION));

    }

    /**
     * 批量写流
     * 
     * @param recordList
     * @return
     */
    private static PutRecordBatchResult putRecordBatch(List<Record> recordList,
            String deliveryStreamName) {
        PutRecordBatchRequest putRecordBatchRequest = new PutRecordBatchRequest();
        putRecordBatchRequest.setDeliveryStreamName(deliveryStreamName);
        putRecordBatchRequest.setRecords(recordList);
        return firehoseClient.putRecordBatch(putRecordBatchRequest);
    }

    /**
     * 更新流配置
     *
     * @throws Exception
     */
    public static void updateDeliveryStream(String deliveryOpenStreamName,
            String s3DestinationUpdateName) throws Exception {
        DeliveryStreamDescription deliveryStreamDescription = describeDeliveryStream(deliveryOpenStreamName);
        LOGGER.info("Updating DeliveryStream Destination: "
                + deliveryOpenStreamName + " with new configuration options");
        // get(0) -> DeliveryStream currently supports only one destination per
        // DeliveryStream
        UpdateDestinationRequest updateDestinationRequest = new UpdateDestinationRequest()
                .withDeliveryStreamName(deliveryOpenStreamName)
                .withCurrentDeliveryStreamVersionId(
                        deliveryStreamDescription.getVersionId())
                .withDestinationId(
                        deliveryStreamDescription.getDestinations().get(0)
                                .getDestinationId());

        S3DestinationUpdate s3DestinationUpdate = new S3DestinationUpdate();
        s3DestinationUpdate.withPrefix(s3DestinationUpdateName);

        updateDestinationRequest.setS3DestinationUpdate(s3DestinationUpdate);

        firehoseClient.updateDestination(updateDestinationRequest);
    }

    /**
     * Method to describe the delivery stream.
     *
     * @param deliveryStreamName
     *            the delivery stream
     * @return the delivery description
     */
    private static DeliveryStreamDescription describeDeliveryStream(
            String deliveryStreamName) {
        DescribeDeliveryStreamRequest describeDeliveryStreamRequest = new DescribeDeliveryStreamRequest();
        describeDeliveryStreamRequest
                .withDeliveryStreamName(deliveryStreamName);
        DescribeDeliveryStreamResult describeDeliveryStreamResponse = firehoseClient
                .describeDeliveryStream(describeDeliveryStreamRequest);
        return describeDeliveryStreamResponse.getDeliveryStreamDescription();
    }

}
