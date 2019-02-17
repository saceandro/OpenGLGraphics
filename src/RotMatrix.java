public class RotMatrix implements Rotation {
    private final double xx, xy, xz, yx, yy, yz, zx, zy, zz;
    
    private RotMatrix(double xx, double xy, double xz,
		      double yx, double yy, double yz,
		      double zx, double zy, double zz) {
	this.xx = xx; this.xy = xy; this.xz = xz;
	this.yx = yx; this.yy = yy; this.yz = yz;
	this.zx = zx; this.zy = zy; this.zz = zz;
    }
    
    private RotMatrix multiply(RotMatrix b) {
	RotMatrix a = this;
	return new RotMatrix(a.xx*b.xx + a.xy*b.yx + a.xz*b.zx,
			     a.xx*b.xy + a.xy*b.yy + a.xz*b.zy,
			     a.xx*b.xz + a.xy*b.yz + a.xz*b.zz,
			     a.yx*b.xx + a.yy*b.yx + a.yz*b.zx,
			     a.yx*b.xy + a.yy*b.yy + a.yz*b.zy,
			     a.yx*b.xz + a.yy*b.yz + a.yz*b.zz,
			     a.zx*b.xx + a.zy*b.yx + a.zz*b.zx,
			     a.zx*b.xy + a.zy*b.yy + a.zz*b.zy,
			     a.zx*b.xz + a.zy*b.yz + a.zz*b.zz);
    }
    
    public void setMatrix(double[] matrix, double[] offset) {
	matrix[0] =xx; matrix[1]=yx; matrix[2] =zx; matrix[3] =0.0;
	matrix[4] =xy; matrix[5]=yy; matrix[6] =zy; matrix[7] =0.0;
	matrix[8] =xz; matrix[9]=yz; matrix[10]=zz; matrix[11]=0.0;
	matrix[12]=offset[0]; matrix[13]=offset[1];
	matrix[14]=offset[2]; matrix[15]=1.0;
    }
    
    public Rotation rotAroundAxis(double angle, double x, double y, double z) {
	double d = Math.sqrt(x*x + y*y + z*z);
	x /= d; y /= d; z /= d;
	angle *= Math.PI / 180.0;
	double cos = Math.cos(angle);
	double sin = Math.sin(angle);
	return this.multiply(new RotMatrix(cos + x*x*(1.0-cos),   x*y*(1.0-cos) - z*sin, x*z*(1.0-cos) + y*sin,
					   x*y*(1.0-cos) + z*sin,   cos * y*y*(1.0-cos), y*z*(1.0-cos) - x*sin,
					   x*z*(1.0-cos) - y*sin, y*z*(1.0-cos) + x*sin,   cos + z*z*(1.0-cos)));
    }
    
    public static RotMatrix identity() {
	return new RotMatrix(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
    }
}