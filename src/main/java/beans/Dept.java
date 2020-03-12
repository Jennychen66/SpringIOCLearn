package beans;

public class Dept {
    private  int deptNo;
    private  String dname;

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
//    @Override
//    public String toString(){
//        return "Dept [deptNo]= '" + this.deptNo +"', [deptName] = '" + this.dname;
//    }
    public Dept(){
        System.out.println("Dept constructor function is invoked");
    }



}
