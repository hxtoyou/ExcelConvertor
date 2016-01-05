package pactera;
/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年8月17日 下午4:49:33
 * 
 */
public enum Test {
	 RED("红色", "1"), GREEN("绿色", "2"), BLANK("白色", "3"), YELLO("黄色", "4");
	 private String name;
     private String index;
	 private Test(String name, String index) {
         this.name = name;
         this.index = index;
     }
	 public static String getName(String index) {
         for (Test c : Test.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
     }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	 
}
