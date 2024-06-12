public class entity {

    public final String tag;
    public final boolean destroyable;

    public boolean isdestroyable() {
        return destroyable;
    }

    public entity(String tag,boolean destroyable) {
        this.tag = tag;
        this.destroyable = destroyable;
    }

    @Override
    public String toString() {
        return tag;
    }
}
