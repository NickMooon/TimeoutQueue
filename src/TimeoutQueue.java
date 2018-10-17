import java.util.ArrayList;
import java.util.Date;


public class TimeoutQueue<T> {
    ArrayList<Item> list = new ArrayList<>();
    class Item {
        private long time;
        private T obj;
        public Item(T o, long mills){
            obj=o;
            time = new Date().getTime()+mills;
        }
        public long getTime(){
            return time;
        }
        public T getObj(){
            return obj;
        }

    }
    public void offer(T o, long mills){
        list.add(new Item(o, mills));

    }
    public int size(){
        int j=0;
        for (int i = 0; i <list.size() ; i++) {

        if (list.get(i).getTime()> new Date().getTime()) j++;
        }
        return j;
    }

    public T poll(){
        T tr = null;
        while(tr==null) {
            if (list.get(0).getTime() < new Date().getTime()) {
                list.remove(0);
            }
            else{
                tr=list.get(0).getObj();
            }
        }
        return tr;
    }

    public static void main(String[] args) {
        TimeoutQueue<String> queue = new TimeoutQueue<>();
        queue.offer("show", 2000);
        queue.offer("show1", 1000);
        queue.offer("show2", 3000);
        queue.offer("show3", 2500);
        queue.offer("show4", 4000);
        queue.offer("show5", 500);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.setStackTrace(null);
        }
        System.out.println("первый непросроченный элемент - " + queue.poll());
        System.out.println("Размер очереди - "+queue.size());


    }
}
