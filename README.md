# 设计模式

黑马设计模式详解：https://www.bilibili.com/video/BV1Np4y1z7BU

笔记参考：https://luzhenyu.blog.csdn.net/article/details/122311416

# 3 软件设计原则

## 3.1 开闭原则

  对扩展开放，对修改关闭。在程序需要进行拓展时，不能修改原有代码。想达到这种效果，需要使用抽象类和接口。

## 3.2 里氏代换原则

任何基类出现的地方，子类一定可以出现。

子类可以拓展父类的功能，但不能改变父类的功能。

换句话说，子类继承父类时，除了添加新功能外，不要重写父类的方法。

```
正方形继承长方形，重写了setLength方法，违反里氏代换原则。
```

## 3.3 依赖倒转原则

  高层模块不应该依赖底层模块，两者应该依赖其抽象；抽象不应该依赖细节，细节应该抽象。简单的说，就是要对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块之间的耦合。

```
比如组装电脑。只需要，内存，机箱cpu，硬盘，只要这些东西有了，就能运行计算机。但具体的品种可以选择。
```

## 3.4 接口隔离原则

  客户端不应该被迫依赖于它不适用的方法。一个类对另一个类的依赖应该建立在最小的接口上。

## 3.5 迪米特法则

又叫最少知识原则。

只和你的直接朋友交谈，不跟“陌生人”说话。

  其含义是：如果两个软件实体无须直接通信，那么就不应当发生直接的互相调用，可以通过第三方（包含了这两个软件）转发该调用。其目的是降低类之间的耦合度，提高模块的相对独立性。

  迪米特法则中的“朋友”是指：当前对象本身、当前对象的成员对象，当前对象创建的对象、当前对象的方法参数等。这些对象同当前对象存在关联、聚合或者组合关系，可以直接访问对象的方法。

## 3.6 合成复用原则

  尽量使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现。

  采用组合或者聚合复用时，可以将已有对象纳入新对象中，使之成为新对象的一部分，新对象可以调用已有对象的功能。

# 4 创建者模式

创建型模式的主要关注点是“怎样创建对象？”，它的主要特点是“将对象的创建与使用分离”。

这样可以降低系统的耦合度，使用者不需要关注对象的创建细节。

创建型模式分为：

- 单例模式
- 工厂方法模式
- 抽象工程模式
- 原型模式
- 建造者模式

## 4.1 单例设计模式

单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。



单例设计模式分类两种：

- 饿汉式：类加载就会导致该单实例对象被创建	
- 懒汉式：类加载不会导致该单实例对象被创建，而是首次使用该对象时才会创建
- 枚举方式

饿汉式-两种方式：①静态方法、②静态代码块：instance对象是随着类的加载而创建的。如果该对象足够大的话，而**一直没有使用就会造成内存的浪费。**

懒汉式-四种方式：①线程不安全、②线程安全、③双重检查锁、**④静态内部类方式**



**静态内部类方式：**

​	静态内部类单例模式中实例由内部类创建，由于 JVM 在加载外部类的过程中, 是不会加载静态内部类的, 只有内部类的属性/方法被调用时才会被加载, 并初始化其静态属性。静态属性由于被 `static` 修饰，保证只被实例化一次，并且严格保证实例化顺序。

​	静态内部类单例模式是一种优秀的单例模式，是开源项目中比较常用的一种单例模式。在没有加任何锁的情况下，保证了多线程下的安全，并且没有任何性能影响和空间的浪费。



- JDK中Runtime类就是使用的单例设计模式。

### 4.1.4 JDK源码解析-Runtime类

Runtime类就是使用的单例设计模式。

1. 通过源代码查看使用的是哪儿种单例模式

   ```java
   public class Runtime {
       private static Runtime currentRuntime = new Runtime();

       /**
        * Returns the runtime object associated with the current Java application.
        * Most of the methods of class <code>Runtime</code> are instance
        * methods and must be invoked with respect to the current runtime object.
        *
        * @return  the <code>Runtime</code> object associated with the current
        *          Java application.
        */
       public static Runtime getRuntime() {
           return currentRuntime;
       }

       /** Don't let anyone else instantiate this class */
       private Runtime() {}
       ...
   }
   ```

   从上面源代码中可以看出Runtime类使用的是恶汉式（静态属性）方式来实现单例模式的。


1. 使用Runtime类中的方法

## 4.2 工厂模式

在java中，万物皆对象，这些对象都需要创建，如果创建的时候直接new该对象，就会对该对象耦合严重，假如我们要更换对象，所有new对象的地方都需要修改一遍，这显然违背了软件设计的开闭原则。**如果我们使用工厂来生产对象，我们就只和工厂打交道就可以了，彻底和对象解耦，如果要更换对象，直接在工厂里更换该对象即可**，达到了与对象解耦的目的；所以说，工厂模式最大的优点就是：**解耦**。

在本教程中会介绍三种工厂的使用

- 简单工厂模式（不属于GOF的23种经典设计模式）
- 工厂方法模式
- 抽象工厂模式

### 4.2.2 简单工厂模式

简单工厂不是一种设计模式，反而比较像是一种编程习惯。

**4.2.2.1 结构**

简单工厂包含如下角色：

- 抽象产品 ：定义了产品的规范，描述了产品的主要特性和功能。
- 具体产品 ：实现或者继承抽象产品的子类
- 具体工厂 ：提供了创建产品的方法，调用者通过该方法来获取产品。



**优点：**

封装了创建对象的过程，可以通过参数直接获取对象。把对象的创建和业务逻辑层分开，这样以后就避免了修改客户代码，如果要实现新产品直接修改工厂类，而不需要在原代码中修改，这样就降低了客户代码修改的可能性，更加容易扩展。

**缺点：**

增加新产品时还是需要修改工厂类的代码，违背了“开闭原则”。



**静态工厂**

在开发中也有一部分人将工厂类中的创建对象的功能定义为静态的，这个就是静态工厂模式，它也不是23种设计模式中的。`public static Coffee createCoffee(String type)`

### 4.2.3 工厂方法模式

针对上例中的缺点，使用工厂方法模式就可以完美的解决，完全遵循开闭原则。

**4.2.3.1 概念**

定义一个用于创建对象的接口，让子类决定实例化哪个产品类对象。工厂方法使一个产品类的实例化延迟到其工厂的子类。

**4.2.3.2 结构**

工厂方法模式的主要角色：

- 抽象工厂（Abstract Factory）：提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法来创建产品。
- 具体工厂（ConcreteFactory）：主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
- 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能。
- 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。



**优点：**

- 用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
- 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；

**缺点：**

- 每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。





### 4.2.4 抽象工厂模式

前面介绍的工厂方法模式中考虑的是一类产品的生产，如畜牧场只养动物、电视机厂只生产电视机、传智播客只培养计算机软件专业的学生等。

这些工厂只生产同种类产品，同种类产品称为同等级产品，也就是说：工厂方法模式只考虑生产同等级的产品，但是在现实生活中许多工厂是综合型的工厂，能生产多等级（种类） 的产品，如电器厂既生产电视机又生产洗衣机或空调，大学既有软件专业又有生物专业等。

本节要介绍的抽象工厂模式将考虑多等级产品的生产,**将同一个具体工厂所生产的位于不同等级的一组产品称为一个产品族。**

![在这里插入图片描述](https://img-blog.csdnimg.cn/03ca0850493e4cab90089d0c99c10f4b.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5rSb5pmT5YWz,size_20,color_FFFFFF,t_70,g_se,x_16)

**概念：**

是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且访问类无须指定所要产品的具体类就能得到**同族的不同等级的产品**的模式结构。

抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。



**抽象工厂模式的主要角色如下：**

- 抽象工厂（Abstract Factory）：提供了创建产品的接口，它包含**多个创建产品的方法**，可以创建多个不同等级的产品。
- 具体工厂（Concrete Factory）：主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
- 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品。
- 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它 同具体工厂之间是多对一的关系。



**优点：**

当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。

**缺点：**

当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。



**使用场景：**

- 当需要创建的对象是一系列相互关联或相互依赖的产品族时，如电器工厂中的电视机、洗衣机、空调等。
- 系统中有多个产品族，但每次只使用其中的某一族产品。如有人只喜欢穿某一个品牌的衣服和鞋。
- 系统中提供了产品的类库，且所有产品的接口相同，客户端不依赖产品实例的创建细节和内部结构。

如：输入法换皮肤，一整套一起换。生成不同操作系统的程序。

### 4.2.5 模式扩展(IOC原理)

**简单工厂+配置文件解除耦合**

可以通过工厂模式+配置文件的方式解除工厂对象和产品对象的耦合。在工厂类中加载配置文件中的全类名，并创建对象进行存储，客户端如果需要对象，直接进行获取即可。

```java
american=com.itheima.pattern.factory.config_factory.AmericanCoffee
latte=com.itheima.pattern.factory.config_factory.LatteCoffee

public class CoffeeFactory {

    //加载配置文件，获取配置文件中配置的全类名，并创建该类的对象进行存储
    //1,定义容器对象存储咖啡对象
    private static HashMap<String,Coffee> map = new HashMap<String, Coffee>();

    //2,加载配置文件， 只需要加载一次
    static {
        //2.1 创建Properties对象
        Properties p = new Properties();
        //2.2 调用p对象中的load方法进行配置文件的加载
        InputStream is = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            p.load(is);
            //从p集合中获取全类名并创建对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                String className = p.getProperty((String) key);
                //通过反射技术创建对象
                Class clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.newInstance();
                //将名称和对象存储到容器中
                map.put((String)key,coffee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //根据名称获取对象
    public static Coffee createCoffee(String name) {
        return map.get(name);
    }
}
```

静态成员变量用来存储创建的对象（键存储的是名称，值存储的是对应的对象），而读取配置文件以及创建对象写在静态代码块中，目的就是只需要执行一次。

### 4.2.6 JDK源码解析-Collection.iterator方法

```java
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("令狐冲");
        list.add("风清扬");
        list.add("任我行");

        //获取迭代器对象
        Iterator<String> it = list.iterator();
        //使用迭代器遍历
        while(it.hasNext()) {
            String ele = it.next();
            System.out.println(ele);
        }
    }
}
```

对上面的代码大家应该很熟，使用迭代器遍历集合，获取集合中的元素。而单列集合获取迭代器的方法就使用到了工厂方法模式。我们看通过类图看看结构：

<img src="https://img-blog.csdnimg.cn/ddc8212e7f1f46bb9df437611f64a23a.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5rSb5pmT5YWz,size_20,color_FFFFFF,t_70,g_se,x_16" style="zoom:75%;" />

Collection接口是抽象工厂类，ArrayList是具体的工厂类；Iterator接口是抽象商品类，ArrayList类中的Iter内部类是具体的商品类。在具体的工厂类中iterator()方法创建具体的商品类的对象。

> 另：
>
> ​	1,DateForamt类中的getInstance()方法使用的是工厂模式；
>
> ​	2,Calendar类中的getInstance()方法使用的是工厂模式；

## 4.3 原型模式

**4.3.1 概述**

用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型对象相同的新对象。

**4.3.2 结构**

原型模式包含如下角色：

- 抽象原型类：规定了具体原型对象必须实现的的 clone() 方法。
- 具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
- 访问类：使用具体原型类中的 clone() 方法来复制新的对象。

**4.3.3 实现**

原型模式的克隆分为浅克隆和深克隆。

> ​	浅克隆：创建一个新对象，**新对象的属性**和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
>
> ​	深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
>
> 简言之：

- **浅克隆，当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制。**
- **深克隆，除了对象本身被复制外，对象所包含的所有成员变量也将复制。**

> 浅克隆类似 “快捷方式”，深克隆才是真正的 “复制文件”。



Java中的Object类中提供了 `clone()` 方法来实现**浅克隆**。

Java 中，如果需要**实现深克隆，可以通过覆盖 Object 类 的 clone() 实现（不安全），也可以通过序列化(Serialization)（常用，自定义类需要实现Serializable接口）等方式**来实现。

如果引用类型里面还包含很多引用类型，或 者内层引用类型的类里面又包含引用类型，使用 clone 方法就会很麻烦。这时可以用序列化的方式来实现对象的深克隆。

> **一、数据类型**
>
> 基本数据类型的特点：直接存储在栈(stack)中的数据
> 引用数据类型的特点：存储的是该对象在栈中引用，真实的数据存放在堆内存里
>
> 引用数据类型在栈中存储了指针，该指针指向堆中该实体的起始地址。当解释器寻找引用值时，会首先检索其在栈中的地址，取得地址后从堆中获得实体。
>
> **二、浅拷贝和深拷贝**
>
> 深拷贝和浅拷贝是只针对Object和Array这样的引用数据类型的。
>
> 浅拷贝只复制指向某个对象的指针，而不复制对象本身，新旧对象还是共享同一块内存。
>
> 但深拷贝会另外创造一个一模一样的对象，新对象跟原对象不共享内存，修改新对象不会改到原对象。







## 4.5 建造者模式

**4.4.1 概述**

将一个复杂对象的构建与表示分离，使得同样的构建过程可以创建不同的表示。

<img src="https://img-blog.csdnimg.cn/4500f7b11d1841f2a4b01f30737c988c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6JCM5a6F6bm_5ZCM5a2m,size_1,color_FFFFFF,t_70,g_se,x_16" style="zoom:60%;" />

- 分离了部件的构造(由Builder来负责)和装配(由Director负责)。 从而可以构造出复杂的对象。这个模式适用于：某个对象的构建过程复杂的情况。
- 由于实现了构建和装配的解耦。不同的构建器，相同的装配，也可以做出不同的对象；相同的构建器，不同的装配顺序也可以做出不同的对象。也就是**实现了构建算法、装配算法的解耦，实现了更好的复用。**
- 建造者模式可以将部件和其组装过程分开，一步一步创建一个复杂的对象。用户只需要指定复杂对象的类型就可以得到该对象，而无须知道其内部的具体构造细节。

**4.4.2 结构**

建造者（Builder）模式包含如下角色：

- 抽象建造者类（Builder）：这个接口规定要实现复杂对象的那些部分的创建，并不涉及具体的部件对象的创建。 
- 具体建造者类（ConcreteBuilder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。在构造过程完成后，提供产品的实例。 
- 产品类（Product）：要创建的复杂对象。
- 指挥者类（Director）：调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。 



**指挥者用来指挥建造顺序，建造者只管造部件**

**指挥者与抽象工厂模式区别：**

抽象工厂模式就是既要造部件又要组装。建造者模式：由具体建造者造部件，指挥者组装

- 指挥者模式是抽象工厂模式其中的一种



**优点：**

- 建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，一般产品类和建造者类是比较稳定的，因此，将主要的业务逻辑封装在指挥者类中对整体而言可以取得比较好的稳定性。
- 在建造者模式中，客户端不必知道产品内部组成的细节，**将产品本身与产品的创建过程解耦**，使得相同的创建过程可以创建不同的产品对象。
- 可以更加精细地控制产品的创建过程 。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。
- 建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成，基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。符合开闭原则。

**缺点：**

- 建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。


## 4.6 创建者模式对比

### 4.6.1 工厂方法模式VS建造者模式

工厂方法模式注重的是整体对象的创建方式；而建造者模式注重的是部件构建的过程，意在通过一步一步地精确构造创建出一个复杂的对象。

我们举个简单例子来说明两者的差异，如要制造一个超人，如果使用工厂方法模式，直接产生出来的就是一个力大无穷、能够飞翔、内裤外穿的超人；而如果使用建造者模式，则需要组装手、头、脚、躯干等部分，然后再把内裤外穿，于是一个超人就诞生了。

### 4.6.2 抽象工厂模式VS建造者模式

抽象工厂模式实现对产品家族的创建，一个产品家族是这样的一系列产品：具有不同分类维度的产品组合，采用抽象工厂模式则是不需要关心构建过程，只关心什么产品由什么工厂生产即可。

建造者模式则是要求按照指定的蓝图建造产品，它的主要目的是通过组装零配件而产生一个新产品。

如果将抽象工厂模式看成汽车配件生产工厂，生产一个产品族的产品，那么建造者模式就是一个汽车组装工厂，通过对部件的组装可以返回一辆完整的汽车。

# 5 结构型模式

结构型模式描述如何将类或对象按某种布局组成更大的结构。它分为**类结构型模式和对象结构型模式**，前者采用**继承机制**来组织接口和类，后者釆用**组合或聚合**来组合对象。

由于组合关系或聚合关系比继承关系耦合度低，满足“合成复用原则”，所以对象结构型模式比类结构型模式具有更大的灵活性。

桥结构型模式分为以下 7 种：（**桥代理组合适配器，享元回家装饰外观**）

- 代理模式
- 适配器模式
- 装饰者模式
- 桥接模式
- 外观模式
- 组合模式
- 享元模式

## 5.1 代理模式

**5.1.1 概述**

由于某些原因需要给某对象提供一个代理以控制对该对象的访问。这时，访问对象不适合或者不能直接引用目标对象，代理对象作为访问对象和目标对象之间的中介。（**买电脑：卖家和厂商中间有代理商**）

Java中的代理按照代理类生成时机不同又分为**静态代理和动态代理**。**静态代理代理类在编译期就生成，而动态代理代理类则是在Java运行时动态生成。动态代理又有JDK代理和CGLib代理两种**。

**5.1.2 结构**

代理（Proxy）模式分为三种角色：

- 抽象主题（Subject）类： 通过接口或抽象类声明真实主题和代理对象实现的业务方法。
- 真实主题（Real Subject）类： 实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
- 代理（Proxy）类 ： 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。

### 5.1.3 静态代理

我们通过案例来感受一下静态代理。

【例】火车站卖票

如果要买火车票的话，需要去火车站买票，坐车到火车站，排队等一系列的操作，显然比较麻烦。而火车站在多个地方都有代售点，我们去代售点买票就方便很多了。这个例子其实就是典型的代理模式，**火车站是目标对象，代售点是代理对象**。类图如下：

<img src="https://img-blog.csdnimg.cn/7accab7a41f149fe8605f3d99b8e1c2c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6JCM5a6F6bm_5ZCM5a2m,size_20,color_FFFFFF,t_70,g_se,x_16" style="zoom:80%;" />

- 测试类直接访问的是ProxyPoint类对象，也就是说ProxyPoint作为访问对象和目标对象的中介。同时也**对sell方法进行了增强**（代理点收取一些服务费用）。

### 5.1.4 JDK动态代理P58

接下来我们使用动态代理实现上面案例，先说说JDK提供的动态代理。Java中提供了一个动态代理类Proxy，Proxy并不是我们上述所说的代理对象的类，而是**提供了一个创建代理对象的静态方法（newProxyInstance方法）来获取代理对象**。

```java
public class ProxyFactory {

    //声明目标对象
    private TrainStation station = new TrainStation();

    //获取代理对象的方法
    public SellTickets getProxyObject() {
        //返回代理对象
        /*
            ClassLoader loader : 类加载器，用于加载代理类。可以通过目标对象获取类加载器
            Class<?>[] interfaces ： 代理类实现的接口的字节码对象
            InvocationHandler h ： 代理对象的调用处理程序
         */
        SellTickets proxyObject = (SellTickets)Proxy.newProxyInstance(
                station.getClass().getClassLoader(),
                station.getClass().getInterfaces(),
                new InvocationHandler() {

                    /*
                        Object proxy : 代理对象。和proxyObject对象是同一个对象，在invoke方法中基本不用
                        Method method ： 对接口中的方法进行封装的method对象
                        Object[] args ： 调用方法的实际参数

                        返回值： 方法的返回值。
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //System.out.println("invoke方法执行了");
                        System.out.println("代售点收取一定的服务费用(jdk动态代理)");
                        //执行目标对象的方法
                        Object obj = method.invoke(station, args);
                        return obj;
                    }
                }
        );
        return proxyObject;
    }
}

```

JDK 动态代理分析
思考以下问题：

ProxyFactory 是代理类吗？

ProxyFactory 不是代理模式中所说的代理类，而是程序在运行过程中动态的在内存中生成的代理类。

通过阿里巴巴开源的 Java 诊断工具（Arthas【阿尔萨斯】）查看代理类的结构：（去除其他代码）

```java
// 程序运行过程中动态生成的代理类
public final class $Proxy0 extends Proxy implements SellTickets {

  private static Method m3;

    public $Proxy0(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    static {
      m3 = Class.forName("com.itheima.proxy.dynamic.jdk.SellTickets").getMethod("sell", new Class[0]);
      return;
    }
  
    public final void sell() {
      this.h.invoke(this, m3, null);
    }
}

```

v从上面的类中，我们可以看到以下几个信息：

- 代理类 **$Proxy0** 实现了 SellTickets 接口，这也就印证了真实类和代理类实现同样的接口。
- 代理类 $Proxy0 将我们提供了的匿名内部类对象传递给了父类。

```java
// 程序运行过程中动态生成的代理类
public final class $Proxy0 extends Proxy implements SellTickets {
    private static Method m3;

    public $Proxy0(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    static {
        m3 = Class.forName("com.itheima.proxy.dynamic.jdk.SellTickets").getMethod("sell", new Class[0]);
    }
	  
    // ② 根据多态的特性，执行的是代理类 $Proxy0 中的 sell() 方法
    public final void sell() {
	      // ③ 代理类 $Proxy0 中的 sell() 方法中又调用了 InvocationHandler 接口的子实现类对象的 invoke 方法
        this.h.invoke(this, m3, null);
    }
}

// Java提供的动态代理相关类
public class Proxy implements java.io.Serializable {
	protected InvocationHandler h;
	 
	protected Proxy(InvocationHandler h) {
        this.h = h;
    }
}

// 代理工厂类
public class ProxyFactory {

    private TrainStation station = new TrainStation();

    public SellTickets getProxyObject() {
        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(station.getClass().getClassLoader(),
                station.getClass().getInterfaces(),
                new InvocationHandler() {
                    // ④ invoke 方法通过反射执行了真实对象所属类 TrainStation 中的 sell() 方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理点收取一些服务费用(JDK动态代理方式)");
                        Object result = method.invoke(station, args);
                        return result;
                    }
                });
        return sellTickets;
    }
}


// 测试访问类
public class Client {
    public static void main(String[] args) {
        // 获取代理对象
        ProxyFactory factory = new ProxyFactory();
      	// ① 在测试类中通过代理对象调用 sell() 方法
        SellTickets proxyObject = factory.getProxyObject();
        proxyObject.sell();
    }
}

```



**动态代理的执行流程是什么样？**

执行流程如下：

① 在测试类中通过代理对象调用 sell() 方法

② 根据多态的特性，执行的是代理类 $Proxy0 中的 sell() 方法

③ 代理类 $Proxy0 中的 sell() 方法中又调用了 InvocationHandler 接口的子实现类对象的 invoke 方法

④ invoke 方法通过反射执行了真实对象所属类 TrainStation 中的 sell() 方法





### 5.1.5 CGLIB 动态代理

上面的案例中，如果没有定义 SellTickets 接口，只定义了 TrainStation 火车站类。则 JDK 代理无法使用，因为 JDK 动态代理要求必须定义接口，它只能对接口进行代理。

CGLIB 是一个功能强大，高性能的代码生成包，它可以为没有实现接口的类提供代理，为 JDK 的动态代理提供了很好的补充



### 三种代理的对比

两种动态代理的对比：**JDK 代理** 和 **CGLib 代理**

- CGLib 动态代理，底层采用 ASM 字节码生成框架，使用字节码技术生成代理类，在 JDK1.6 之前比使用 JDK 动态代理的效率要高。唯一需要注意的是，**CGLib 不能对声明为 final 的类或者方法进行代理**，因为CGLib 的原理是动态生成被代理类的子类。
- JDK1.6、JDK1.7、JDK1.8 逐步对 JDK 动态代理优化之后，在调用次数较少的情况下，JDK 代理效率高于 CGLib 代理效率；只有当进行大量调用的时候，JDK1.6 和 JDK1.7 比 CGLib 代理效率低一点，但是到 JDK1.8 的时候，JDK 代理效率高于 CGLib 代理。
- 所以，JDK1.8 以后，**如果有接口就使用 JDK 动态代理，没有接口就使用 CGLib 代理**。

动态代理和静态代理的对比：

- 动态代理与静态代理相比较，最大的好处是**接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理 (InvocationHandler.invoke)**。这样，在接口方法数量比较多的时候，我们可以进行灵活处理，而不需要像静态代理那样每一个方法进行中转。
- 如果接口增加一个方法，静态代理模式除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法，增加了代码维护的复杂度，而动态代理不会出现该问题。



**5.1.6 优缺点**

**优点：**

- 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；
- 代理对象可以扩展目标对象的功能；
- 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度；

> 保护、增强、解耦

**缺点：**

- 增加了系统的复杂度；



**5.1.8 使用场景**

- 远程（Remote）代理

  本地服务通过网络请求远程服务。为了实现本地到远程的通信，我们需要实现网络通信，处理其中可能的异常。为良好的代码设计和可维护性，我们将网络通信部分隐藏起来，只暴露给本地服务一个接口，通过该接口即可访问远程服务提供的功能，而不必过多关心通信部分的细节。

  > RPC 的思想

- 防火墙（Firewall）代理

  当你将浏览器配置成使用代理功能时，防火墙就将你的浏览器的请求转给互联网；当互联网返回响应时，代理服务器再把它转给你的浏览器。

  > VPN 的思想

- 保护（Protect or Access）代理

  控制对一个对象的访问，如果需要，可以给不同的用户提供不同级别的使用权限。

## 5.2 适配器模式

**定义**：将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。

- 适配器模式分为**类适配器模式**和**对象适配器模式**，前者由于类之间的耦合度比后者高，且要求程序员了解现有组件库中的相关组件的内部结构，所以应用相对较少些。



**结构**

适配器模式（Adapter）包含以下主要角色：

- **目标（Target）接口**：当前系统业务所期望访问的接口，它可以是抽象类或接口。
- **适配者（Adaptee）类**：它是被访问和适配的现存组件库中的组件接口。
- **适配器（Adapter）类**：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。



### 类适配器模式

实现方式：定义一个适配器类来**实现当前系统的业务接口**，同时又继承**现有组件库中已经存在的组件**。

> 实现业务接口，继承已有组件

【例】读卡器

现有一台电脑只能读取 SD 卡，而要读取 TF 卡中的内容的话就需要使用到适配器模式。

创建一个读卡器，将 TF 卡中的内容读取出来，类图如下：

- SDCard 是**目标接口**，它是 Computer 所期望访问的接口
- TFCard 是**适配者类**，它将会**被适配**到 Computer 可以访问的 SDCard
- SDAdapterTF 是**适配器类**，它实现让目标访问 SDCard 却达到 TFCard 的功能。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2ae0974b9f454fc4a44d074be0aa9cf0.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6JCM5a6F6bm_5ZCM5a2m,size_20,color_FFFFFF,t_70,g_se,x_16)



## 对象适配器模式

实现方式：对象适配器模式可釆用将现有组件库中已经实现的组件引入适配器类中，该类同时实现当前系统的业务接口。

> 实现业务接口，引入已有组件

【例】读卡器

我们使用对象适配器模式将读卡器的案例进行改写，类图如下：

> 注意 SDAdapterTF 不再是直接继承 TFCardImpl，而是聚合 TFCard 接口

![在这里插入图片描述](https://img-blog.csdnimg.cn/a9400fef522e4314b3d7d6a85c0b3da5.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6JCM5a6F6bm_5ZCM5a2m,size_20,color_FFFFFF,t_70,g_se,x_16)