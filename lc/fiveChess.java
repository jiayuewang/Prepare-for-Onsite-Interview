
	<pre name="code" class="java">//定义棋盘大小，宽w，高h
	int w = 11;
	int h = w;
	//用一个二维数组保存棋盘数据，1代表红棋，2代表黑棋
	int[][] chess = new int[h][w];
	//定义控制循环的布尔变量
	boolean game_over = false;
	boolean win_red = false;
	boolean win_black = false;

//判断输赢的函数，传入当前点位置坐标
		public void judge(int y3, int x3) {
			//创建4个StringBuffer对象分别保存横向，纵向，两个斜向棋盘数据
			StringBuffer buf21 = new StringBuffer();
			StringBuffer buf14 = new StringBuffer();
			StringBuffer buf13 = new StringBuffer();
			StringBuffer buf24 = new StringBuffer();
			// 横向数据流
			for (int y = y3, x = 0; x < w; x++) {
				buf21.append(chess[y][x]);
			}
			//System.out.println(buf21);
			
			// 纵向数据流
			for (int y = 0, x = x3; y < h; y++) {
				buf14.append(chess[y][x]);
			}
			//System.out.println(buf14);
			
			// 二四象限数据流
			if (y3 >= x3) {
				for (int y = y3 - x3, x = 0; y < h; y++, x++) {
					buf24.append(chess[y][x]);
				}
			} else {
				for (int y = 0, x = x3 - y3; x < w; y++, x++) {
					buf24.append(chess[y][x]);
				}
			}
			//System.out.println(buf24);
 
			// 一三象限数据流
			if ((x3 + y3) < h) {
				for (int x = x3 + y3, y = 0; y <= x3 + y3; y++, x--) {
					buf13.append(chess[y][x]);
				}
			} else {
				for (int x = h - 1, y = x3 + y3 - (h - 1); y < h; y++, x--) {
					buf13.append(chess[y][x]);
				}
			}
			//System.out.println(buf13);
 
			//使用正则表达式匹配数据判断输赢，连续5个1表示红棋赢，连续5个二表示黑棋赢
			if (buf21.toString().matches("\\d*1{5}\\d*")
					|| buf14.toString().matches("\\d*1{5}\\d*")
					|| buf13.toString().matches("\\d*1{5}\\d*")
					|| buf24.toString().matches("\\d*1{5}\\d*")) {
				win_red =true;
			}
			
			if (buf21.toString().matches("\\d*2{5}\\d*")
					|| buf14.toString().matches("\\d*2{5}\\d*")
					|| buf13.toString().matches("\\d*2{5}\\d*")
					|| buf24.toString().matches("\\d*2{5}\\d*")) {
				win_black =true;
			}
			
			if (win_red) {
				JOptionPane.showMessageDialog(null, "红棋赢！");
			} else if (win_black) {
				JOptionPane.showMessageDialog(null, "黑棋赢！");
			}

