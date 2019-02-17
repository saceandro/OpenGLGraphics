public interface Rotation {
    public void setMatrix(double[] matrix, double[] offset);
    
    public Rotation rotAroundAxis(double angle, double x, double y, double z);
}