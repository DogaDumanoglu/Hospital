public class Operation_Imaging extends ExaminationDecorator  {
    public Operation_Imaging(ExaminationDecorator d) {
        this.cost = 10;/*Part of decorator pattern for calculating the cost*/
        this.next = d;
    }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "imaging ";
        if (next != null) {
            output += next.toString();
        }
        return output;
    }
}
