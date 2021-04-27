public interface ExaminationInterface {
    /*Part of decorator pattern*/
    void addOperation(ExaminationDecorator operations);
    int cost();
    String toStringWithTab();
}
