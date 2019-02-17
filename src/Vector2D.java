public class Vector2D{
    private final double x,y;
    
    public Vector2D(double x, double y){
	this.x = x;
	this.y = y;
    }
    public double x(){
	return this.x;
    }
    public double y(){
	return this.y;
    }
    public double norm(){
	return Math.sqrt(this.x*this.x + this.y*this.y);
    }
    public Vector2D add(Vector2D v){
	return new Vector2D(this.x+v.x, this.y+v.y);
    }
    public Vector2D subtract(Vector2D v){
	return new Vector2D(this.x-v.x, this.y-v.y);
    }
    public Vector2D scale(double d){
	return new Vector2D(this.x*d,this.y*d);
    }
    public Vector2D normalize(){
	double norm = this.norm();
	if (norm > Tolerance.EPSILON)
	    return this.scale(1.0/norm);
	else
	    return (new Vector2D(0.0,0.0));
    }
    public String toString(){
	return "(" + x + ", " + y + ")";
    }
}