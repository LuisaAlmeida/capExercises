package luisa.almeida.secondrestaurantapi.util;

public class Pair<F, S> {

    public F _first;
    public S _second;

    public Pair(F _first, S _second) {
        this._first = _first;
        this._second = _second;
    }

    public Pair(){
    }

    public F get_first() {
        return _first;
    }

    public void set_first(F _first) {
        this._first = _first;
    }

    public S get_second() {
        return _second;
    }

    public void set_second(S _second) {
        this._second = _second;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_first == null) ? 0 : _first.hashCode());
        result = prime * result + ((_second == null) ? 0 : _second.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair<?, ?> other = (Pair<?, ?>) obj;
        if (_first == null) {
            if (other._first != null)
                return false;
        } else if (!_first.equals(other._first))
            return false;
        if (_second == null) {
            if (other._second != null)
                return false;
        } else if (!_second.equals(other._second))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "_first=" + _first +
                ", _second=" + _second +
                '}';
    }
}

