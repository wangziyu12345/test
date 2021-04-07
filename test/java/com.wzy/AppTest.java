package com.wzy;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.wzy.entiy.Person;
import com.wzy.entiy.TimeUtils;
import com.wzy.entiy.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest extends Person
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void Map(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("book","书");
        map.put("apple","苹果");
        map.put("computer","计算机");

        String key = "book";

        //获取key，拿到value
        map.get(key);
        //删除
        map.remove(key);

        if(map.containsKey(key)){
            System.out.println("map中包含键名"+key);
        } else {
            System.out.println("map中不包含键名"+key);
        }
    }

    @Test
    public void spilt() throws ParseException {
        List<String> list = Arrays.asList( "李四", "王五","张三");

        //java7排序
        /*Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1); //返回值大于0表示正序,小于0表示逆序
            }
        });*/
        //System.out.println(list);

        //java8排序
        Collections.sort(list,(s1,s2) -> s1.compareTo(s2));
        System.out.println(list);

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",20));
        personList.add(new Person("王五",19));
        personList.add(new Person("李四",26));

        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge()<o2.getAge()){
                    return 1; //表示正序
                } else if (o1.getAge()>o2.getAge()){
                    return -1; //表示逆序
                }
                return o2.getAge()-o1.getAge();
            }
        });

        /*personList.sort(((o1, o2) -> {
            return o2.getAge()-o1.getAge();
        }));*/


        System.out.println(personList);

    }

    @Test
    public void test20() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        ArrayList<User> list1 = new ArrayList<>();
        User user = new User();
        user.setName("张三");
        user.setAge(15);
        user.setBirthday(simpleDateFormat.parse("2021-03-15 15:12:54"));
        list1.add(user);
        User user1 = new User();
        user1.setName("张三");
        user1.setAge(17);
        user1.setBirthday(simpleDateFormat.parse("2021-03-19 15:12:54"));
        list1.add(user1);

        //根据时间倒序排序
        List<User> collect = list1.stream().sorted(Comparator.comparing(User::getBirthday).reversed()).collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void test(){

        String str = "123456";
        //System.out.println(str.substring(2));

        // 其日历字段已由当前日期和时间初始化：
        Calendar rightNow = Calendar.getInstance(); // 子类对象
        // 获取年
        int year = rightNow.get(Calendar.YEAR);
        // 获取月
        int month = rightNow.get(Calendar.MONTH);
        // 获取日
        int date = rightNow.get(Calendar.DATE);
        //获取几点
        int hour=rightNow.get(Calendar.HOUR_OF_DAY);
        //获取上午下午
        int moa=rightNow.get(Calendar.AM_PM);
        //获取这一天是在这个月的第多少天
        int i = rightNow.get(Calendar.DAY_OF_MONTH);
        if(moa==1){
            //System.out.println("下午");
        }else{
            //System.out.println("上午");
        }

        //System.out.println(year + "年" + (month + 1) + "月" + date + "日"+hour+"时");
        rightNow.add(Calendar.YEAR,5);
        rightNow.add(Calendar.DATE, -10);
        int year1 = rightNow.get(Calendar.YEAR);
        int date1 = rightNow.get(Calendar.DATE);
        //System.out.println(year1 + "年" + (month + 1) + "月" + date1 + "日"+hour+"时");
    }

    @Test
    public void test1() throws ParseException {
        String s1 = "aaa,bbb,ccc";
        String[] arr = s1.split(",");
        for (String s2 : arr) {
            System.out.println(s2);
        }
        System.out.println(arr.length);
        System.out.println(arr[0]);

        String begin = "2020-05-06 12:02:56";
        String end = "2020-05-07 1:02:56";
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date beginTime = sft.parse(begin);
        Date endTime = sft.parse(end);
    }

    @Test
    public void test2() throws ParseException {
        String time = "2020-07-05 23:32:12";
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        int stopTime = 30;
        //字符串转成date类型 没有0点
        Date parse = sft.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(Calendar.MINUTE,stopTime);
        Date time1 = calendar.getTime();
        String format = sft.format(time1);
        System.out.println(format);

        //在这个时间过后的90分钟后的时间 有0点
        DateTime dateTime = DateUtil.offsetMinute(parse, stopTime);
        System.out.println(dateTime);

        Calendar instance = Calendar.getInstance();
        //3年后的5天前
        instance.add(Calendar.YEAR,3);
        instance.add(Calendar.DATE,-5);
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int date = instance.get(Calendar.DATE);
        int hour = instance.get(Calendar.HOUR);
        int minute = instance.get(Calendar.MINUTE);
        int second = instance.get(Calendar.SECOND);
        System.out.println(year+"年"+month+"月"+date+"日"+hour+"时"+minute+"分"+second+"秒");

        //获得这一天是在这个月的多少天
        int i = instance.get(Calendar.DAY_OF_MONTH);
        //获得这一天是在这一年的第多少天
        int i1 = instance.get(Calendar.DAY_OF_YEAR);
        //获得这一天是在这一周的第多少天
        int i2 = instance.get(Calendar.DAY_OF_WEEK);

    }

    @Test
    public void test3(){

        String str3 = "12,13";
        Integer i = 12;
        //等于-1就说明在这个字符串里面没有找到这个字符 不等于就相反
        if (str3.indexOf("12") != -1){
            //如果这个字符串里面包含了12就会执行这里面的方法
            System.out.println("1111111111");
        }
        String str = "1,2,3,_4";
        //返回指定字符的索引
        System.out.println(str.indexOf("1"));
        //返回指定索引的字符
        System.out.println(str.charAt(0));
        //字符串的替换
        System.out.println(str.replace(",","-"));
        //字符串里面的个数
        System.out.println(str.split(",").length);
        //字符串的截取
        System.out.println(str.substring(1,2));
        //判断字符串里面有没有存在这个字符
        System.out.println(str.contains(","));
        //截取"_"后面的内容
        System.out.println(str.substring(str.indexOf("_")+1));
        //字符串转成集合
        List<String> list = Arrays.asList(str.split(","));
        //集合转成字符串
        System.out.println(StringUtils.join(list.toArray(),","));

        String str1 = null;
        String str2 = "2";
        //用工具类比较可以避免空指针异常 str1.equals(str2)用这个比较就会报空指针异常
        //Objects.equals(str1,str2);
        if (!StringUtils.equals(str1, str2)){
            System.out.println("111");
        } else {
            System.out.println("222");
        }
    }

    @Test
    public void c(){

        String str = "abcabcabcabdcabc";
        Pattern pattern = Pattern.compile("a");
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while (findMatcher.find()) {
            number++;
            if (number == 2) {//当“a”第二次出现时停止
                break;
            }
        }
        int start = findMatcher.start();//“a”第二次出现的位置
        System.out.println("'a'第二次出现的位置是：" + start);

        String[] arr = {"1","2","3"};

        List list = Arrays.asList(arr);
        System.out.println(list);

        //获取cpu核数
        int result = Runtime.getRuntime().availableProcessors();
        System.out.println(result);

    }

    @Test
    public void test4(){
        /*String [] arr = {"丑时","寅时","卯时","辰时","巳时","午时","未时","申时","酉时","戌时","亥时"};
        for (int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }*/
        String[] arr = new String[11];
        arr[0]="丑时";
        arr[1]="寅时";
        arr[2]="卯时";
        arr[3]="辰时";
        arr[4]="巳时";
        arr[5]="午时";
        arr[6]="未时";
        arr[7]="申时";
        arr[8]="酉时";
        arr[9]="戌时";
        arr[10]="亥时";
        for (int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

        String s = new String("123");
        String s1 = new String("123");
        System.out.println(s==s1);


    }

    @Test
    public void test5(){
        /*StringBuffer sb = new StringBuffer();
        sb.append("8");
        sb.append("t");
        System.out.println(sb);*/
        Integer totalAmount = 55350;
        BigDecimal bigDecimal = new BigDecimal(totalAmount);
        BigDecimal bigDecimal1 = new BigDecimal(100);
        //System.out.println(bigDecimal.divide(bigDecimal1));

        String str = "12,13";
        Integer i = 12;
        //等于-1就说明在这个字符串里面没有找到这个字符 不等于就相反
        if (str.indexOf("12") != -1){
            //如果这个字符串里面包含了12就会执行这里面的方法
            System.out.println("1111111111");
        }

        System.out.println(str.contains("12,13,14"));

        Long l = 1L;
        String s = "1";
        if (l.equals(Long.valueOf(s))){
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }


    }

    private String updateStatus(Integer status){
        switch (status){
            case 0:
                return "待付款";
            case 1:
                return "已付款";
            case 2:
                return "已完成";
            default:
                return null;
        }
    }

    @Test
    public void test6() throws ParseException {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        /*for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }*/
        //list.forEach(System.out::println);
        //System.out.println(list.stream().filter(s -> s.equals("1")).collect(Collectors.toList()));

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(date).replace("-",""));

    }

    @Test
    public void test12(){
        /*ArrayList<Person> list = Lists.newArrayListWithExpectedSize(3);
        list.add(new Person("张三",20));
        list.add(new Person("李四",21));
        list.add(new Person("王五",22));*/

        //==可以用来比较基本数据类型和引用数据类型,比较基本数据类型的时候就就比较他们的值是否相等,引用数据类型的时候就比较他们的地址值是否相等
        //equals只能比较引用数据类型,是比较里面的内容是否一致
        String str = "12";
        String str1 = "12";
        String s = new String("12");
        System.out.println(str==str1);
        System.out.println(str.equals(str1));
        System.out.println(str==s);
        System.out.println(str.equals(s));


        new Thread(){
            public void run(){
                try {
                    Thread.sleep(5000);
                    System.out.println("love");
                } catch (InterruptedException e) { }
            }
        }.start();

    }

    public static void main(String[] args) {
        /*new Thread(){
            public void run(){
                try {
                    Thread.sleep(5000);
                    System.out.println("love");
                } catch (InterruptedException e) { }
            }
        }.start();*/
        Calendar calendar = Calendar.getInstance();
        /*calendar.setTime(TimeUtils.strToTime("2020-05-06 12:02:56"));
        int day=calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+1);
        String s = TimeUtils.formatToTime(calendar.getTime());*/
        //String day = getDay("2020-05-06 12:02:56");
        //System.out.println(s);

        calendar.add(Calendar.DATE,-1);
        System.out.println(TimeUtils.formatToTime(calendar.getTime()));
    }

    public static String getDay(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date=null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day=calendar.get(Calendar.DATE);
        //此处修改为+1则是获取后一天，-1则是获取前一天
        calendar.set(Calendar.DATE,day+1);
        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    @Test
    public void test7(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("jack",20));
        list.add(new Person("jack",10));
        list.add(new Person("tom",20));
        list.add(new Person("mick",30));

        Map<Integer, List<Person>> collect2 = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("==============>"+collect2);


        List<Person> collect = list.stream()
                //.filter(person -> person.getAge() == 20) //保留年龄为20的
                .limit(2) //返回前两个元素
                .skip(1) //去除前一个元素
                .collect(Collectors.toList()); //将stream流转换为list
        //根据年龄分组
        Map<Integer, List<Person>> collect1 = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect1);
        //System.out.println(collect);
        //list.stream().forEach(System.out::println);

        ArrayList<String> strings = new ArrayList();
        strings.add("1");
        strings.add("3");
        strings.add("2");
        //对字符串的排序 升序 倒序的话就是把a和b比较位置换一下
        strings.sort((a, b) -> a.compareTo(b.toString()));
        //对数字的排序
        //strings.sort((a, b) -> a.compareTo(b.intValue()));
        System.out.println(strings);

    }
    

    @Test
    public void test8(){
        /*String str = "ababccccan";
        int num = 0;
        for (int i = 0;i<str.length();i++){
            if (((Character)(str.charAt(i))).equals('a')){
                // 累加统计次数
                num++;
            }
        }
        System.out.println(num);*/

        /*ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("张四");
        list.add("王五");
        list.stream()
                .filter((s) -> s.startsWith("张"));*/
        /*Arrays.asList("a","b","c")
                .stream()
                .findFirst()
                .ifPresent(System.out ::println);*/
        /*Integer result = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer o : list) {
            System.out.println(result += o);
        }*/

        /*Optional.ofNullable(list).filter(o -> o.size()>1).ifPresent(o -> o.stream().forEach(a ->{
            if (a.equals(2)){
                System.out.println("======>"+a);
            }
        }));*/

        String str = "";
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        for (String string : strings) {
            str += string + ",";
        }
        System.out.println(str);

        ArrayList<Person> list = new ArrayList<>();
        ArrayList<Person> list2 = new ArrayList<>();
        if (list.size()+list2.size()>1){
            System.out.println("======");
        }

    }

    @Test
    public void  test21(){

        Integer count = 1;
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("jack",20));
        list.add(new Person("jack",10));
        list.add(new Person("tom",20));
        list.add(new Person("mick",30));

        System.out.println(list);

        List<User> list1 = Lists.transform(list,person -> {
            User user = new User();
            BeanUtils.copyProperties(person,user);
            user.setAge(person.getAge() + count);
            return user;
        });
        //需要重新进行封装 要不然会报错
        //ArrayList<User> list2 = new ArrayList<>(list1);
        //System.out.println(list2);
        //根据年龄倒序排序
        List<User> collect = list1.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        /*Collections.sort(list1, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getAge() - o1.getAge();
            }
        });*/
        //System.out.println(list2);
        //System.out.println(collect);

        /*List<Person> list2 = list.stream().map(t -> {
            t.setAge(t.getAge() + count);
            return t;
        }).collect(Collectors.toList());*/

    }

    @Test
    public void get() throws ParseException {
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int result = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,result - 1);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime()));*/
        Integer i = 0;
        List<Integer> integers = Arrays.asList(2, 1, 3);
        for (Integer integer : integers) {
            i += integer;
        }
        System.out.println(i);

        System.out.println(LocalDate.now().toString()+" 00:00:00");

        String time = "2021-03-30 12:12:12";
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(time));

    }

    @Test
    public void  test9(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("jack",20));
        list.add(new Person("jack",20));
        list.add(new Person("tom",20));
        list.add(new Person("mick",20));

        List<Integer> collect1 = list.stream().map(Person::getAge).distinct().collect(Collectors.toList());
        System.out.println("============>"+collect1);

        List<Person> collect = list.stream()
                //会把符合这里面条件的数据查出来
                .filter(person -> person.getAge() != 20 && person.getName().startsWith("j"))
                .collect(Collectors.toList());


        //如果list集合符合filter里面的条件的就会执行ifPresent里面的内容
        Optional.ofNullable(list).filter(o -> o.size()>0).ifPresent(o ->o.stream().forEach(person -> {
            //System.out.println(list);
        }));


        Set<Person> set = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //字符串,则按照asicc码升序排列

                return o1.getName().compareTo(o2.getName());
            }
        });
        set.addAll(list);
        //System.out.println(new ArrayList<>(set));

        //System.out.println(collect1);
        System.out.println(collect);


    }

    @Test
    public void getDay() {
        String day = "2018-03-19 12:12:12";
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd hh:mm:ss").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime());
        System.out.println(dayAfter);
    }

    @Test
    public void sss() throws ParseException {
        /*String format = "yyyy-MM-dd HH:mm:ss";
        Date nowTime = new SimpleDateFormat(format).parse("2020-02-01 09:27:00");
        Date startTime = new SimpleDateFormat(format).parse("2020-01-01 09:27:00");
        Date endTime = new SimpleDateFormat(format).parse("2020-02-03 09:27:59");
        boolean effectiveDate = isEffectiveDate(nowTime, startTime, endTime);
        if (effectiveDate){
            System.out.println("是");
        } else {
            System.out.println("否");
        }*/
        Long memberId = 0L;
        ArrayList<Long> list = new ArrayList<>();
        list.add(0L);
        list.add(2L);
        list.add(3L);
        System.out.println("没有移除之前====》"+list);
        list.remove(0L);
        System.out.println("移除之后====》"+list);
        if (list.contains(memberId)){
            System.out.println("包含");
        } else {
            System.out.println("不包含");
        }

        double s = 100/3;
        ArrayList<Double> objects = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            objects.add(s);
        }
        System.out.println(objects);
    }

    @Test
    public void test10(){

        String s = "10";
        Integer integer = Integer.valueOf(s);
        if (integer>1&&integer<=10){
            System.out.println("1111111"); //亚健康
        } else if (integer>10){
            System.out.println("2222"); //异常
        }
        System.out.println(LocalDate.now().getYear());
        System.out.println(LocalDate.now().getMonth());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println(LocalDate.now().getDayOfWeek());
        System.out.println(LocalDate.now().getDayOfYear());
    }

    /**
     * 判断一个时间是否在时间范围内
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}
