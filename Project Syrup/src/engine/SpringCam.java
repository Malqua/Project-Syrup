package engine;

import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import engine.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class SpringCam{
	
	public SpringCam(){
		
	}
	
	public void lookAt(Vector3f target){
		m_target = target;
	}
	
	public void lookAt(Vector3f eye, Vector3f target, Vector3f up){
		m_eye = eye;
		m_target = target;
		m_targetYAxis = up;
		
		Vector3f.sub(eye, target, m_zAxis);
		m_zAxis.normalise();
		
		Vector3f.cross(m_zAxis, DEFAULT_NEGATIVE_CONSTANT, m_viewDir);
		
		Vector3f.cross(up, m_zAxis, m_xAxis);
		m_xAxis.normalise();
		
		Vector3f.cross(m_zAxis, m_xAxis, m_yAxis);
		m_yAxis.normalise();
		m_xAxis.normalise();
		
		float ent1 = -(Vector3f.dot(m_xAxis, eye));
		float ent2 = -(Vector3f.dot(m_yAxis, eye));
		float ent3 = -(Vector3f.dot(m_zAxis, eye));
		
	    m_viewMatrix.m00= m_xAxis.x;
	    m_viewMatrix.m10 = m_xAxis.y;
	    m_viewMatrix.m20 = m_xAxis.z;
	    m_viewMatrix.m30 = ent1;

	    m_viewMatrix.m01 = m_yAxis.x;
	    m_viewMatrix.m11 = m_yAxis.y;
	    m_viewMatrix.m21 = m_yAxis.z;
	    m_viewMatrix.m31 = ent2;

	    m_viewMatrix.m02 = m_zAxis.x;
	    m_viewMatrix.m12 = m_zAxis.y;
	    m_viewMatrix.m22 = m_zAxis.z;    
	    m_viewMatrix.m32 = ent3;
	    
	    m_orientation.toRotationMatrix(m_viewMatrix);
	    
	    Vector3f offset = new Vector3f();
	    Vector3f.sub(m_target, m_eye, offset);
	    
	    							//Magnitude equation
	    m_offsetDistance = (float) Math.sqrt(offset.x * offset.x + offset.y * offset.y + offset.z * offset.z);

	}
	
	public void perspective(float fovx, float aspect, float znear, float zfar){
	
		float e = 1.0f / (float)Math.tan(Math.toRadians((double)fovx) / 2.0f);
		float aspectInv = 1.0f / aspect;
		float fovy = (float) (2.0f * Math.atan(aspectInv / e));
		float xScale = (float) (1.0f / Math.tan(0.5f * fovy));
		float yScale = xScale / aspectInv;
		
	    m_projMatrix.m00 = xScale;
	    m_projMatrix.m01 = 0.0f;
	    m_projMatrix.m02 = 0.0f;
	    m_projMatrix.m03 = 0.0f;

	    m_projMatrix.m10 = 0.0f;
	    m_projMatrix.m11 = yScale;
	    m_projMatrix.m12 = 0.0f;
	    m_projMatrix.m13 = 0.0f;

	    m_projMatrix.m20 = 0.0f;
	    m_projMatrix.m21 = 0.0f;
	    m_projMatrix.m22 = (zfar + znear) / (znear - zfar);
	    m_projMatrix.m23 = -1.0f;

	    m_projMatrix.m30 = 0.0f;
	    m_projMatrix.m31 = 0.0f;
	    m_projMatrix.m32 = (2.0f * zfar * znear) / (znear - zfar);
	    m_projMatrix.m33 = 0.0f;

	    m_fovx = fovx;
	    m_znear = znear;
	    m_zfar = zfar;
		
	}
	
	public void rotate(float longitudeDegrees, float latitudeDegrees){
		
	    m_headingDegrees = -longitudeDegrees;
	    m_pitchDegrees = -latitudeDegrees;
	}
	
	public void update(float elapsedTimeSec){
	    updateOrientation(elapsedTimeSec);

	    if (m_enableSpringSystem)
	        updateViewMatrix(elapsedTimeSec);
	    else
	        updateViewMatrix();
	}
	
	//Getter methods
	
	public float getDampingConstant(){
		return m_dampingConstant;
	}
	
	public float getOffsetDistance(){
		return m_offsetDistance;
	}
	
	public Quaternion getOrientation(){
		return m_orientation;
	}
	
	public Vector3f getPosition(){
		return m_eye;
	}
	
	public Matrix getProjectionMatrix(){
		return m_projMatrix;
		
	}
	
	public float getSpringConstant(){
		return m_springConstant;
	}
	
	public Vector3f getTargetYAxis(){
		return m_targetYAxis;
	}
	
	public Vector3f getViewDirection(){
		return m_viewDir;
	}
	
	public Matrix getViewMatrix(){
		return m_viewMatrix;
	}
	
	public Vector3f getXAxis(){
		return m_xAxis;
	}
	
	public Vector3f getYAxis(){
		return m_yAxis;
	}
	
	public Vector3f getZAxis(){
		return m_zAxis;
	}
	
	public boolean springSystemIsEnabled(){
		return m_enableSpringSystem;
	}
	
	//setter methods
	
	public void enableSpringSystem(boolean enableSpringSystem){
		m_enableSpringSystem = enableSpringSystem;
	}
	
	public void setOffsetDistance(float offsetDistance){
		m_offsetDistance = offsetDistance;
	}
	
	public void setHeadingDegrees(float headingDegrees){
		m_headingDegrees = headingDegrees;
	}

	public void setPitchDegrees(float pitchDegrees){
		m_pitchDegrees = pitchDegrees;
	}
	
	public void setSpringConstant(float springConstant){
		m_springConstant = springConstant;
	}
	
	public void setDampingConstant(float dampingConstant){
		m_dampingConstant = dampingConstant;
	}
	/*
	public void setSpringConstant(float springConstant){
		m_springConstant = springConstant;
		m_dampingConstant = 2.0f * (float) Math.sqrt(springConstant);
	}
	*/
	public void setFOVX(float FOVX){
		m_fovx = FOVX;
	}
	
	public void setZNear(float ZNear){
		m_znear= ZNear;
	}
	
	public void setZFar(float ZFar){
		m_zfar = ZFar;
	}
	
	public void setEye(float X, float Y, float Z){
		m_eye = new Vector3f(X,Y,Z);
	}

	public void setTarget(float X, float Y, float Z){
		m_target = new Vector3f(X,Y,Z);
	}
	
	public void setTarget(Vector3f target){
		m_target = target;
	}
	
	public void setTargetYAxis(float X, float Y, float Z){
		m_targetYAxis = new Vector3f(X,Y,Z);
	}
	
	public void setXAxis(float X, float Y, float Z){
		m_xAxis = new Vector3f(X,Y,Z);
	}

	public void setYAxis(float X, float Y, float Z){
		m_yAxis = new Vector3f(X,Y,Z);
	}
	
	public void setZAxis(float X, float Y, float Z){
		m_zAxis = new Vector3f(X,Y,Z);
	}
	
	public void setViewDir(float X, float Y, float Z){
		m_viewDir = new Vector3f(X,Y,Z);
	}
	
	public void setVelocity(float X, float Y, float Z){
		m_velocity = new Vector3f(X,Y,Z);
	}
	
	public void viewMatrixIdentity(){
		m_viewMatrix.setIdentity();
	}
	
	public void projMatrixIdentity(){
		m_projMatrix.setIdentity();
	}
	
	public void orientationIdentity(){
		m_orientation.loadIdentity();
	}
	
	private void updateOrientation(float elapsedTimeSec){
	    m_pitchDegrees *= elapsedTimeSec;
	    m_headingDegrees *= elapsedTimeSec;

	    engine.Quaternion rot = new engine.Quaternion();

	    if (m_headingDegrees != 0.0f)
	    {
	        rot.fromAngleAxis(m_headingDegrees, m_targetYAxis);
	        m_orientation = rot.mult(m_orientation);
	    }

	    if (m_pitchDegrees != 0.0f)
	    {
	        rot.fromAngleAxis(m_pitchDegrees, WORLD_XAXIS);
	        m_orientation = m_orientation.mult(rot);
	    }
	}
	
	private void updateViewMatrix(){
		m_orientation.toRotationMatrix(m_viewMatrix);

	    m_xAxis.set(m_viewMatrix.m00, m_viewMatrix.m10, m_viewMatrix.m20);
	    m_yAxis.set(m_viewMatrix.m01, m_viewMatrix.m11, m_viewMatrix.m21);
	    m_zAxis.set(m_viewMatrix.m02, m_viewMatrix.m12, m_viewMatrix.m22);
	    m_zAxis.negate(m_viewDir);

	    //Multiplying Vector3f and Floats.
	    float x = m_zAxis.x * m_offsetDistance;
	    float y = m_zAxis.y * m_offsetDistance;
	    float z = m_zAxis.z * m_offsetDistance;
	    
	    Vector3f Scaled = new Vector3f(x,y,z);	  
	    // -- //
	    Vector3f.add(m_target, Scaled, m_eye);

	    m_viewMatrix.m30 = -Vector3f.dot(m_xAxis, m_eye);
	    m_viewMatrix.m31 = -Vector3f.dot(m_yAxis, m_eye);
	    m_viewMatrix.m32 = -Vector3f.dot(m_zAxis, m_eye);
	}
	
	private void updateViewMatrix(float elapsedTimeSec){
		m_orientation.toRotationMatrix(m_viewMatrix);

	    m_xAxis.set(m_viewMatrix.m00, m_viewMatrix.m10, m_viewMatrix.m20);
	    m_yAxis.set(m_viewMatrix.m01, m_viewMatrix.m11, m_viewMatrix.m21);
	    m_zAxis.set(m_viewMatrix.m02, m_viewMatrix.m12, m_viewMatrix.m22);

	    // Calculate the new camera position. The 'idealPosition' is where the
	    // camera should be position. The camera should be positioned directly
	    // behind the target at the required offset distance. What we're doing here
	    // is rather than have the camera immediately snap to the 'idealPosition'
	    // we slowly move the camera towards the 'idealPosition' using a spring
	    // system.
	    //
	    // References:
	    //   Stone, Jonathan, "Third-Person Camera Navigation," Game Programming
	    //     Gems 4, Andrew Kirmse, Editor, Charles River Media, Inc., 2004.

	    Vector3f idealPosition = new Vector3f();
	    
	    float x = m_zAxis.x * m_offsetDistance;
	    float y = m_zAxis.y * m_offsetDistance;
	    float z = m_zAxis.z * m_offsetDistance;
	    
	    Vector3f Scaled = new Vector3f(x,y,z);
	    
	    Vector3f.add(m_target, Scaled, idealPosition);
	    
	    Vector3f displacement = new Vector3f();
	    Vector3f.sub(m_eye, idealPosition, displacement);
	    
	    Vector3f springAcceleration = new Vector3f();
	    
	    float xx = -m_springConstant * displacement.x;
	    float yy = -m_springConstant * displacement.y;
	    float zz = -m_springConstant * displacement.z;
	    
	    Vector3f disp = new Vector3f(xx,yy,zz);
	    float x1 = m_dampingConstant * m_velocity.x;
	    float y1 = m_dampingConstant * m_velocity.y;
	    float z1 = m_dampingConstant * m_velocity.z;
	    
	    Vector3f damp = new Vector3f(x1,y1,z1);
	    
	    Vector3f.sub(disp, damp, springAcceleration);

	    float t1 = springAcceleration.x * elapsedTimeSec;
	    float t2 = springAcceleration.y * elapsedTimeSec;
	    float t3 = springAcceleration.z * elapsedTimeSec;
	    Vector3f sAeTS = new Vector3f(t1, t2, t3);
	    
	    Vector3f.add(m_velocity, sAeTS, m_velocity);
	    
	    float e1 = m_velocity.x * elapsedTimeSec;
	    float e2 = m_velocity.y * elapsedTimeSec;
	    float e3 = m_velocity.z * elapsedTimeSec;
	    Vector3f mveTS = new Vector3f(e1, e2, e3);
	    
	    Vector3f.add(m_eye, mveTS, m_eye);

	    // The view matrix is always relative to the camera's current position
	    // 'm_eye'. Since a spring system is being used here 'm_eye' will be
	    // relative to 'idealPosition'. When the camera is no longer being
	    // moved 'm_eye' will become the same as 'idealPosition'. The local
	    // x, y, and z axes that were extracted from the camera's orientation
	    // 'm_orienation' is correct for the 'idealPosition' only. We need
	    // to recompute these axes so that they're relative to 'm_eye'. Once
	    // that's done we can use those axes to reconstruct the view matrix.

	    Vector3f.sub(m_eye, m_target, m_zAxis);
	    m_zAxis.normalise();

	    Vector3f.cross(m_targetYAxis, m_zAxis, m_xAxis);
	    m_xAxis.normalise();

	    Vector3f.cross(m_zAxis, m_xAxis, m_yAxis);
	    m_yAxis.normalise();

	    m_viewMatrix.setIdentity();

	    m_viewMatrix.m00 = m_xAxis.x;
	    m_viewMatrix.m10 = m_xAxis.y;
	    m_viewMatrix.m20 = m_xAxis.z;
	    m_viewMatrix.m30 = -Vector3f.dot(m_xAxis, m_eye);

	    m_viewMatrix.m01 = m_yAxis.x;
	    m_viewMatrix.m11 = m_yAxis.y;
	    m_viewMatrix.m21 = m_yAxis.z;
	    m_viewMatrix.m31 = -Vector3f.dot(m_yAxis, m_eye);

	    m_viewMatrix.m02 = m_zAxis.x;
	    m_viewMatrix.m12 = m_zAxis.y;
	    m_viewMatrix.m22 = m_zAxis.z;   
	    m_viewMatrix.m32 = -Vector3f.dot(m_zAxis, m_eye);

	    m_zAxis.negate(m_viewDir);
	}
	
    static float DEFAULT_SPRING_CONSTANT = 16.0f;
    static float DEFAULT_DAMPING_CONSTANT = 8.0f;
    static float DEFAULT_FOVX = 1.0f;
    static float DEFAULT_ZFAR = 1000.0f;
    static float DEFAULT_ZNEAR = 1.0f;
    private static Vector3f DEFAULT_NEGATIVE_CONSTANT = new Vector3f(-1f,-1f,-1f);
    static Vector3f WORLD_XAXIS = new Vector3f(1.0f, 0.0f, 0.0f);
    static Vector3f WORLD_YAXIS = new Vector3f(0.0f, 1.0f, 0.0f);
    static Vector3f WORLD_ZAXIS = new Vector3f(0.0f, 0.0f, 1.0f);
	
    private boolean m_enableSpringSystem;
    private float m_springConstant;
    private float m_dampingConstant;
    private float m_offsetDistance;
    private float m_headingDegrees;
    private float m_pitchDegrees;
    private float m_fovx;
    private float m_znear;
    private float m_zfar;
    private Vector3f m_eye;
    private Vector3f m_target;
    private Vector3f m_targetYAxis;
    private Vector3f m_xAxis;
    private Vector3f m_yAxis;
    private Vector3f m_zAxis;
    private Vector3f m_viewDir;
    private Vector3f m_velocity;
    private Matrix4f m_viewMatrix;
    private Matrix4f m_projMatrix;
    private Quaternion m_orientation;
}