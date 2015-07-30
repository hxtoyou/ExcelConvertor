package exportExcel.utils;
/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年7月20日 下午5:28:35
 * 
 */
public enum CardType {
	VOUCHER(1,"voucher"),DECLARE(2,"declare"),MSGS(3,"msgs"),DOCS(4,"docs");
	   // 成员变量
    private String value;
    private int index;

	private CardType(int index,String value){
		  this.value = value;
          this.index = index;
	}
	public String getValue(){
		return this.value;
	}
}
