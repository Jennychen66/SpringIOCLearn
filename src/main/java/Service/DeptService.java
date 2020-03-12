package Service;

import Do.DeptDo;

public class DeptService {
    public DeptDo getaDo() {
        return aDo;
    }

    public void setaDo(DeptDo aDo) {
        this.aDo = aDo;
    }

    private DeptDo aDo;

    // 这就是依赖注入
    public void addDo(){
        aDo.save();
    }
}
