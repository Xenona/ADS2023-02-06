package by.it.group251003.pelih.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item o) {
            //тут может быть ваш компаратор


            return 0;
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item[] items = new by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        Comparator <by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item> StopSort = new Comparator<by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item>() {
            @Override
            public int compare(by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item o1, by.it.group251003.pelih.lesson02.C_GreedyKnapsack.Item o2) {
                if (o1.cost/o1.weight > o2.cost/o2.weight)
                    return -1;
                if (o1.cost/o1.weight < o2.cost/o2.weight)
                    return 1;
                else
                    return 0;
            }
        };
        Arrays.sort(items,StopSort);
        int CurentWeight = 0;
        int CurrentCost = 0;
        int diff;
        int i =0;
        boolean Flag;
        while (CurentWeight < W){
            CurentWeight = CurentWeight + items[i].weight;
            CurrentCost = CurrentCost + items[i].cost;
            diff = W - CurentWeight;
            if (diff < items[i+1].weight){
                CurentWeight = CurentWeight + diff;
                CurrentCost = CurrentCost + diff*items[i+1].cost/items[i+1].weight;
            }
            i++;
        }
        result = CurrentCost;

        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item

        //ваше решение.





        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new by.it.group251003.pelih.lesson02.C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}