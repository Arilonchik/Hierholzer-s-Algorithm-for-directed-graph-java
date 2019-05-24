import java.util.*;

public class Graph_Geizeth {
    //Создаем коллекцию счетчика вершин
     private HashMap<Integer,Integer> counter = new HashMap<>();

    // Создаём стек для сохрания пути из вершин
    private Stack<Integer> currentPath = new Stack<>();

    //Создаем список конечных вершин из которого формируется цикл Эйлера
    private ArrayList<Integer> finalCycle = new ArrayList<>();

    //Метод вычисляющий цикл Эйлера, принимает в себя список смежности
    private void printCycle(ArrayList<ArrayList<Integer>> ad_list){

        /*Считтая, что ориентированный граф данный на вход сильносвязанный, и все его вершины имеют четную степень
        можем начинать с любой вершины, в конкретном случае с первой (0)
        */
        currentPath.push(0);
        int curr_v = 0;


        for (int i=0; i<ad_list.size(); i++)
        {
            //Ставим ассоциативность ключ-значение (вершина - колличество ребер)
            counter.put(i,ad_list.get(i).size());
        }

        //Работаем в цикле, пока стек пути не станет 0 по длинне
        while (!currentPath.empty())
        {
            //Проверяем остались ли у вершины непройденные ребра
            if (counter.get(curr_v) != 0)
            {
                //Вставляем в стек пути текущую вершину
                currentPath.push(curr_v);

                //Высчитываем следующую вершину к которой есть ребро из списка смежности
                int next_v = ad_list.get(curr_v).get(0);

                //Удаляем ребро по которому "прошли"
                try {
                    counter.put(curr_v,counter.get(curr_v)-1);
                    ad_list.get(curr_v).remove(0);
                }catch (IndexOutOfBoundsException e){
                    curr_v = next_v;
                    continue;

                }
                curr_v = next_v;
            }

            //Если у вершины не осталось рёбер
            else
            {
                //Записываем в цикл Эйлера текущую вершину
                finalCycle.add(finalCycle.size(),curr_v);

                //Делаем шаг назад, забираем со стека последнюю вершину и удаляем в нем
                curr_v = currentPath.peek();
                currentPath.pop();
            }
        }

        //После окончания цикла, выводим список цикла в обратном порядке
        for (int i=finalCycle.size()-1; i>=0; i--)
        {
            System.out.print(finalCycle.get(i)); if(i!=0) System.out.print("->");

        }
    }


    public static  void main(String[] args)
    {
        //Создаем новый граф и даем ему на вход список смежности
        ArrayList<ArrayList<Integer>> adjacentList = new ArrayList<>();
        pushToAdj(0,1, adjacentList);
        pushToAdj(1,0, adjacentList);
        pushToAdj(0,2, adjacentList);
        pushToAdj(2,0, adjacentList);
        pushToAdj(0,3, adjacentList);
        pushToAdj(3,0, adjacentList);
        pushToAdj(0,4, adjacentList);
        pushToAdj(4,0, adjacentList);
        pushToAdj(0,5, adjacentList);
        pushToAdj(5,0, adjacentList);
        pushToAdj(6,0, adjacentList);
        pushToAdj(0,6, adjacentList);
        pushToAdj(0,7, adjacentList);
        pushToAdj(7,0, adjacentList);
        pushToAdj(0,8, adjacentList);
        pushToAdj(8,0, adjacentList);
        pushToAdj(0,9, adjacentList);
        pushToAdj(9,0, adjacentList);
        pushToAdj(0,10, adjacentList);
        pushToAdj(10,0, adjacentList);
        pushToAdj(0,11, adjacentList);
        pushToAdj(11,0, adjacentList);
        //Запускаем нахождение цикла
        Graph_Geizeth graph = new Graph_Geizeth();
        graph.printCycle(adjacentList);

    }

    //Метод для вставки элементов списка смежности в список в программе
   private static void pushToAdj(int start, int end, ArrayList<ArrayList<Integer>> ad){
        try {
            ad.get(start).add(ad.get(start).size(), end);
        }catch (IndexOutOfBoundsException e){
            ad.add(start, new ArrayList<>());
            pushToAdj(start,end,ad);
        }

    }

    }
