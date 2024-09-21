public class Int {
    int val;

    public Int(int val) {
        this.val = val;
    }

    public int getInt(){
        return val;
    }

    public void setInt(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
