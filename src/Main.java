public class Main {
    public static void main(String[] args){
        System.out.println("hello world");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据驱动加载成功");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
