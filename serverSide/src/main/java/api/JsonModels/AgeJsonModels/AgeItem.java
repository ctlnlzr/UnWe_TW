package api.JsonModels.AgeJsonModels;

public class AgeItem {
        String varJudet;
        String varTotal;
        String varSub25ani;
        String var2529ani;
        String var3039ani;
        String var4049ani;
        String var5055ani;
        String varPeste55ani;

        public String getVarJudet() { return varJudet; }
        public void setVarJudet(String varJudet) { this.varJudet = varJudet; }

        public int getVarTotal() { return Integer.parseInt(varTotal); }
        public void setVarTotal(String varTotal) { this.varTotal = varTotal; }

        public int getVarSub25ani() { return Integer.parseInt(varSub25ani); }
        public void setVarSub25ani(String varSub25ani) { this.varSub25ani = varSub25ani; }

        public int getVar2529ani() { return Integer.parseInt(var2529ani); }
        public void setVar2529ani(String var2529ani) { this.var2529ani = var2529ani; }

        public int getVar3039ani() { return Integer.parseInt(var3039ani); }
        public void setVar3039ani(String var3039ani) { this.var3039ani = var3039ani; }

        public int getVar4049ani() { return Integer.parseInt(var4049ani); }
        public void setVar4049ani(String var4049ani) { this.var4049ani = var4049ani; }

        public int getVar5055ani() { return Integer.parseInt(var5055ani); }
        public void setVar5055ani(String var5055ani) { this.var5055ani = var5055ani; }

        public int getVarPeste55ani() { return Integer.parseInt(varPeste55ani); }
        public void setVarPeste55ani(String varPeste55ani) { this.varPeste55ani = varPeste55ani; }

        public AgeItem() { }

}
