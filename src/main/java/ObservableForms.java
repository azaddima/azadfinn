import java.io.Serializable;
import java.util.Observable;

public class ObservableForms extends Observable implements Serializable {

    @Override
    public void notifyObservers(Object arg)
    {
        setChanged();
        super.notifyObservers(arg);
    }
}
