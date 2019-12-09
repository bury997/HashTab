package hashtab;

import java.util.Scanner;

/**
 * 哈希表(散列)
 */
public class HashTabDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashTab hashTab = new HashTab(5);
        while (true){
            System.out.println("add:添加");
            System.out.println("list:遍历");
            System.out.println("find:查找");
            System.out.println("exit退出");
            String input = sc.next();
            switch (input){
                case "add":
                    System.out.println("id");
                    int id = sc.nextInt();
                    System.out.println("name");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("ID");
                    id = sc.nextInt();
                    hashTab.find(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
            }
        }
    }

}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    //头指针  默认null
    private Emp head;

    public void add(Emp emp){
        if (head==null){
            head=emp;
            return;
        }
        Emp currentEmp = head;
        while (true){
            if (currentEmp.next == null){
                break;
            }
            currentEmp = currentEmp.next;
        }
        currentEmp.next = emp;
    }

    public void list(){
        if (head==null){
            System.out.println("当前链表为空");
            return;
        }
        Emp currentEmp = head;
        while (true){
            System.out.print("\t=>id="+currentEmp.id+" ,name="+currentEmp.name);
            if (currentEmp.next==null){
                break;
            }
            currentEmp = currentEmp.next;
        }
        System.out.println();
    }

    public Emp findById(int id){
        if (head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp currentEmp = head;
        while (true){
            if (currentEmp.id==id){
                break;
            }
            if (currentEmp.next==null){
                currentEmp = null;
                break;
            }
            currentEmp = currentEmp.next;
        }
        return currentEmp;
    }
}

class HashTab{
    private int size;
    private EmpLinkedList[] empLinkedListArr;

    public HashTab(int size){
        this.size = size;
        empLinkedListArr = new EmpLinkedList[size];
        for (int i=0;i<size;i++){
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArr[empLinkedListNo].add(emp);
    }

    public void list(){
        for (int i=0;i<size;i++){
            empLinkedListArr[i].list();
        }
    }

    public void find(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArr[empLinkedListNo].findById(id);
        if (emp!=null){
            System.out.println("找到id为:"+id+" ,姓名为:"+emp.name);
        }else {
            System.out.println("未找到");
        }
    }

    public int hashFun(int id){
        return id % size;
    }
}