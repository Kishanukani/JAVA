import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Emp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            List<Project> projects = new ArrayList<>();
            int a = sc.nextInt();
            sc.nextLine();
            String b = sc.nextLine();
            String c = sc.nextLine();
            int d = sc.nextInt();
            for (int j = 0; j < d; j++) {
                sc.nextLine();
                String e = sc.nextLine();
                int f = sc.nextInt();
                projects.add(new Project(e, f));
            }
            list.add(new Employee(a, b, c, d, projects));
        }

        int a = sc.nextInt();
        int b = sc.nextInt();
        task1(list, a);
        task2(list, b);

    }

    public static void task1(List<Employee> list, int a) {
        try {
            int c = 0;
            HashSet<Integer> set = new HashSet<>();
            for (Employee e : list) {
                if (e.getEmpid() == a) {
                    c++;
                    for (Project p : e.getProjects()) {
                        set.add(p.projRatings());
                    }
                }
            }
            if (c == 0) {
                throw new CstException("EMp not found");
            } else {
                // System.out.println(set);
                for (Integer b : set) {
                    System.out.println(b);
                }
            }

        } catch (CstException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public static void task2(List<Employee> list, int a) {
        try {
            for (Employee e : list) {
                int s = 0;
                for (Project p : e.getProjects()) {
                    // s += i.projRatings();
                    s += p.projRatings();
                }
                s = s / e.getProjCount();
                if (s >= a) {
                    System.out.println(e.getEname());
                } else if (s <= a) {
                    throw new CstException("null");
                }
            }
        } catch (CstException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

}

class CstException extends Exception {
    CstException(String s) {
        super(s);
    }
}

/**
 * Employee
 */
class Employee {
    private int empId;
    private String empName;
    private String cmpName;
    private int projCount;
    private List<Project> projects;

    Employee(int e, String name, String cname, int pc, List<Project> projs) {
        this.empId = e;
        this.empName = name;
        this.cmpName = cname;
        this.projCount = pc;
        this.projects = projs;
    }

    public void setEmpId(int eid) {
        this.empId = eid;
    }

    public void setEmpName(String ename) {
        this.empName = ename;

    }

    public void setcmpName(String cname) {
        this.cmpName = cname;
    }

    public void setprojCount(int c) {
        this.projCount = c;
    }

    public void setProjects(List<Project> proj) {
        this.projects = proj;
    }

    public int getEmpid() {
        return this.empId;
    }

    public String getEname() {
        return this.empName;
    }

    public String getCmpName() {
        return this.cmpName;

    }

    public int getProjCount() {
        return this.projCount;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

}

class Project {
    private String projectName;
    private int projRatings;

    Project(String str, int n) {
        this.projectName = str;
        this.projRatings = n;
    }

    public String getProjname() {
        return this.projectName;

    }

    public int projRatings() {
        return this.projRatings;
    }

    public void setProjectName(String pName) {
        this.projectName = pName;
    }

    public void setProjectRating(int n) {
        this.projRatings = n;
    }
}