public class ExaminationDecorator implements ExaminationInterface{
    protected ExaminationDecorator next;
    protected int cost= 0;

    public ExaminationDecorator() {}

    @Override
    public void addOperation(ExaminationDecorator n) {
        this.next = n;
    }


    @Override
    public int cost(){
        /*This function works as a part of decorator pattern for calculating the cost*/
        int t = 0;
        t += this.cost;
        if (this.next != null) {
            t += this.next.cost();}
        return t;
    }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "";
        if (next != null) {
            output += next.toString();
        }
        return output;
    }

    public String toStringWithTab() {
        return this.toString();
    }
}
