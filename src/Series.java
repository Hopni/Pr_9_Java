import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    protected double firstElement;
    protected double coefficient;
    protected int numberOfElements;
    Series(){};
    Series(double first, double coefficient, int n) throws NumberFormatException{
        if(n < 1) throw new NumberFormatException();
        this.firstElement = first;
        this.coefficient = coefficient;
        this.numberOfElements = n;
    };

    abstract double progressionElement(int j);

    public double progressionSum(){
        double sum = 0;
        for(int i = 1; i <= this.numberOfElements; i++){
            sum += this.progressionElement(i);
        }
        return sum;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < this.numberOfElements; i++){
            sb.append(this.progressionElement(i) + " ");
        }
        return sb.toString();
    }

    public void writeIntoFile(String path) throws FileNotFoundException, IOException{
        FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write("Elements: " + this.toString());
            fileWriter.flush();
            fileWriter.close();
    }
}
