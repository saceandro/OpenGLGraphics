public class CubicBezier implements ParametricCurve{
    private Vector2D[] points;
    
    public CubicBezier(Vector2D[] points){
	this.points = points;
    }

    public Vector2D[] points(){
	return points;
    }
    
    public Vector2D evaluate(double t){
	double[] ts = new double[4];
	double u = 1.0 - t;
	ts[0] = u*u*u; ts[1] = 3.0*u*u*t;
	ts[2] = 3.0*u*t*t; ts[3] = t*t*t;
	Vector2D point = new Vector2D(0.0,0.0);
	for (int j=0; j<=3; j++)
	    point = point.add(points[j].scale(ts[j]));
	return point;
    }
    
    public double begin(){
	return 0.0;
    }

    public double end(){
	return 1.0;
    }
}