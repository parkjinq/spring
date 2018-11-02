package kr.or.ddit.board.model;

public class BoardVO {
	
	private int board_id;
	private String board_num;
	private String reg_id;
	
	public BoardVO() {
	}

	public BoardVO(int board_id, String board_num, String reg_id) {
		this.board_id = board_id;
		this.board_num = board_num;
		this.reg_id = reg_id;
	}

	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", board_num=" + board_num + ", reg_id=" + reg_id + "]";
	}
	
}
