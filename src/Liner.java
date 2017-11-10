import java.util.zip.DataFormatException;

public class Liner extends Series {
    Liner(){};
    Liner(double first, double coefficient, int n){
        super(first, coefficient, n);
    }

    public void setFirstElement(double first){
        this.firstElement = first;
    }

    public void setDifference(double diff){
        this.coefficient = diff;
    }

    public void setNumberOfElements(int n) throws DataFormatException{
        if(n < 1) throw new DataFormatException();
        this.numberOfElements = n;
    }

    protected double progressionElement(int j){ return (this.firstElement + (j - 1)*this.coefficient); }
}
