package learn.pft;

public class Square {

    public double l;

//Конструктор
    public Square(double l) {
        this.l = l;
    }

    public double area() {
        return this.l * this.l;
    }
}
