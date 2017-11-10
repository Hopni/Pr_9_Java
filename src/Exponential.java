import java.util.zip.DataFormatException;

public class Exponential extends Series {
    Exponential(){};
    Exponential(double first, double coefficient, int n){
        super(first, coefficient, n);
    }

    public void setFirstElement(double first){
        this.firstElement = first;
    }

    public void setRatio(double ratio){
        this.coefficient = ratio;
    }

    public void setNumberOfElements(int n) throws DataFormatException {
        if(n < 1) throw new DataFormatException();
        this.numberOfElements = n;
    }

    protected double progressionElement(int j){
        return (this.firstElement*Math.pow(this.coefficient, (double)j-1));
    }
}
