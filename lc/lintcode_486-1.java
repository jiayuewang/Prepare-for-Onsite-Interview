思路1：priority queue

将每个list的最小节点放入一个priority queue (min heap)中。
之后每从queue中取出一个节点，则将该节点在其list中的下一个节点插入，

以此类推直到全部节点都经过priority queue。由于priority queue的大小为始终为k，
而每次插入的复杂度是log k，一共插入过nk个节点。时间复杂度为O(nk logk)，
空间复杂度为O(k)。

注意C++的STL中的priority queue默认是max heap，定义一个新的比较