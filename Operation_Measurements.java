public class Operation_Measurements extends ExaminationDecorator{
    public Operation_Measurements(ExaminationDecorator d) {
        this.cost += 5;/*Part of decorator pattern for calculating the cost*/
        this.next = d;
    }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "measurements ";
        if (next != null) {
            output += next.toString();
        }
        return output;
    }
}
