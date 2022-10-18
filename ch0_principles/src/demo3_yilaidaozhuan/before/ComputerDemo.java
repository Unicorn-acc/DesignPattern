package demo3_yilaidaozhuan.before;

/**
 *
 * 但是似乎组装的电脑的cpu只能是Intel的，内存条只能是金士顿的，硬盘只能是希捷的，
 * 这对用户肯定是不友好的，用户有了机箱肯定是想按照自己的喜好，选择自己喜欢的配件。
 * @version v1.0
 * @ClassName: ComputerDemo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class ComputerDemo {
    public static void main(String[] args) {
        //创建组件对象
        XiJieHardDisk hardDisk = new XiJieHardDisk();
        IntelCpu cpu = new IntelCpu();
        KingstonMemory memory = new KingstonMemory();

        //创建计算机对象
        Computer c = new Computer();
        //组装计算机
        c.setCpu(cpu);
        c.setHardDisk(hardDisk);
        c.setMemory(memory);

        //运行计算机
        c.run();
    }
}
