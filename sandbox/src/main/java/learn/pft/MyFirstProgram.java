package learn.pft;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello, World!");


        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " составляет: " + s.area());

        Rectangle r = new Rectangle(5, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " составляет: " + r.area());

    }

//    public static double area(Square s) {
//        return s.l * s.l;
//    }
}