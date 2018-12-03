package demo;

import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUseDemo {
    public static void main(String[] args) {
        imutableSet();
        System.out.println("----------1---------");
        of();
        System.out.println("-----------2--------");
        copyOf();
        System.out.println("------------3-------");
        asList();
        System.out.println("-------------4------");
        mutiSet();
        System.out.println("--------------5-----");
        biMap();
        System.out.println("---------------6----");
        mutiMap();
        System.out.println("---------------7----");
        table();
        System.out.println("---------------8----");
//        iterator();
    }

//    private static void iterator() {
//        List<String> list = Lists.newArrayList("Apple","Pear","Peach","Banana");
//        Predicate<String> condition = new Predicate<String>() {
//            @Override
//            public boolean apply(String input) {
//                return input.startsWith("P");
//            }
//        };
//        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
//        System.out.println("all result == " + allIsStartsWithP);
//
//        String length5Element = Iterators.find(list.iterator(), new Predicate<String>() {
//            @Override
//            public boolean apply(String input) {
//                return input.length() == 5;
//            }
//        });
//    }

    /**
     * 在guava库中还提供了一种二维表结构：Table。使用Table可以实现二维矩阵的数据结构，可以是稀溜矩阵。
     */
    private static void table() {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 5; column++) {
                table.put(row, column, "value of cell (" + row + "," + column + ")");
            }
        }
        for (int row=0;row<table.rowMap().size();row++) {
            Map<Integer,String> rowData = table.row(row);
            for (int column =0;column < rowData.size(); column ++) {
                System.out.println("cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }
    }

    /**
     * Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
     * 实现              	  Key实现 	        Value实现
     * ArrayListMultimap 	   HashMap      	ArrayList
     * HashMultimap 	       HashMap 	        HashSet
     * LinkedListMultimap      LinkedHashMap 	LinkedList
     * LinkedHashMultimap      LinkedHashMap 	LinkedHashSet
     * TreeMultimap 	       TreeMap 	        TreeSet
     * ImmutableListMultimap   ImmutableMap 	ImmutableList
     * ImmutableSetMultimap    ImmutableMap 	ImmutableSet
     */
    private static void mutiMap() {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();

        // 添加键值对
        myMultimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = myMultimap.size();
        System.out.println(size);  // 4

        // 获得Fruits对应的所有的值
        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println(fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables); // [Carrot]

        //遍历Mutlimap
        for(String value : myMultimap.values()) {
            System.out.printf("%s\t",value);
        }

        // Removing a single value
        myMultimap.remove("Fruits","Pear");
        System.out.println();
        System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]

        // Remove all values for a key
        myMultimap.removeAll("Fruits");
        System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
    }

    /**
     * 可以查询key value
     *
     HashBiMap: key 集合与 value 集合都有 HashMap 实现
     EnumBiMap: key 与 value 都必须是 enum 类型
     ImmutableBiMap: 不可修改的 BiMap

     */
    private static void biMap() {
        BiMap<String,String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一","Monday");
        weekNameMap.put("星期二","Tuesday");
        weekNameMap.put("星期三","Wednesday");
        weekNameMap.put("星期四","Thursday");
        weekNameMap.put("星期五","Friday");
        weekNameMap.put("星期六","Saturday");
        weekNameMap.put("星期日","Sunday");

        System.out.println("星期日的英文名是" + weekNameMap.get("星期日"));
        System.out.println("Sunday的中文是" + weekNameMap.inverse().get("Sunday"));
    }


    /**
     *
     HashMultiset: 元素存放于 HashMap
     LinkedHashMultiset: 元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定
     TreeMultiset:元素被排序存放于TreeMap
     EnumMultiset: 元素必须是 enum 类型
     ImmutableMultiset: 不可修改的 Mutiset

     */
    private static void mutiSet() {
        Multiset multiset = HashMultiset.create();
        String sentences = "this is a story, there is a good girl in the story.";
        Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            multiset.add(word);
        }

        for (Object element : multiset.elementSet()) {
            System.out.println((String)element + ":" + multiset.count(element));
        }
    }

    private static void asList() {
        List list = ImmutableSet.of("red", "green", "black", "white", "grey").asList();
        print(list);
    }

    private static void copyOf() {
        ImmutableSet is = ImmutableSet.copyOf(new String[]{"red","green","black","white","grey"});
        print(is);
    }

    private static void of() {
        ImmutableSet is = ImmutableSet.of("red", "green", "black", "white", "grey");
        print(is);
    }

    private static void imutableSet() {
        Set<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green", "black", "white", "grey")
                .build();
        //immutableNamedColors.add("abc");
        print(immutableNamedColors);
    }

    private static void print(Collection<String> immutableNamedColors) {
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
    }
}
