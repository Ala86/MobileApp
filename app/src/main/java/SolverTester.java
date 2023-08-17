
class SolverTester{
    public static void main(String[]args){

        Solver solver = new Solver();

        double quadratic = solver.Quadratic(2.2,4.4,6.8);
        System.out.println("The positive root is = " + quadratic);

        double area = solver.area(23.23);
        System.out.println("the area of the circle is = " + area);


    }
}

class Solver{

    public double Quadratic(double a, double b,double c ){

        a = 2.3; b = 4; c = 5.6;
        double root1, root2;

        // calculate the determinant (b2 - 4ac)
        double determinant = b * b - 4 * a * c;

        // check if determinant is greater than 0
        if (determinant > 0) {

            // two real and distinct roots
            root1 = (-b + Math.sqrt(determinant)) / (2 * a);

            // System.out.format("root1 = %.2f and root2 = %.2f", root1);
            return root1;
        }

        // check if determinant is equal to 0
        else if (determinant == 0) {

            root1 = -b / (2 * a);
            return root1;
        }

        // if determinant is less than zero
        else {

            // roots are complex number and distinct
            double imaginary = Math.sqrt(-determinant) / (2 * a);
            return imaginary;
        }
    }

    public double area(double radius ){
        return Math.PI * radius * radius;

    }

}