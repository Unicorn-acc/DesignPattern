package demo2_lishidaihuan.before;

/**
 * @version v1.0
 * @ClassName: RectangleDemo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class RectangleDemo {

    public static void main(String[] args) {
        //创建长方形对象
        Rectangle r = new Rectangle();
        //设置长和宽
        r.setLength(20);
        r.setWidth(10);
        //调用resize方法进行扩宽
        resize(r);
        printLengthAndWidth(r);

        System.out.println("==================");
        //创建正方形对象
        Square s = new Square();
        //设置长和宽
        s.setLength(10);
        //调用resize方法进行扩宽
        resize(s);
        printLengthAndWidth(s);
        /**
         * 在resize方法中，Rectangle类型的参数是不能被Square类型的参数所代替，如果进行了替换就得不到预期结果。
         * 因此，Square类和Rectangle类之间的继承关系违反了里氏代换原则，它们之间的继承关系不成立，正方形不是长方形。
         */
    }

    //扩宽方法
    public static void resize(Rectangle rectangle) {
        //判断宽如果比长小，进行扩宽的操作
        while(rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }

    //打印长和宽
    public static void printLengthAndWidth(Rectangle rectangle) {
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getWidth());
    }
}
