public class Operation_Tests extends ExaminationDecorator{
    public Operation_Tests(ExaminationDecorator d) {
        this.cost += 7;/*Part of decorator pattern for calculating the cost*/
        this.next = d;
    }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "tests ";
        if (next != null) {
            output += next.toString();
        }
        return output;
    }
}
