package engine;

import engine.SpringCam;

public class SpringCam_CPP{
	
	SpringCam sc = new SpringCam();
	
	public SpringCam_CPP(){
		sc.enableSpringSystem(true);
		sc.setSpringConstant(SpringCam.DEFAULT_SPRING_CONSTANT);
		sc.setDampingConstant(SpringCam.DEFAULT_DAMPING_CONSTANT);
		
		sc.setOffsetDistance(0.0f);
		sc.setHeadingDegrees(0.0f);
		sc.setPitchDegrees(0.0f);
		
		sc.setFOVX(SpringCam.DEFAULT_FOVX);
		sc.setZNear(SpringCam.DEFAULT_ZNEAR);
		sc.setZFar(SpringCam.DEFAULT_ZFAR);
		
		sc.setEye(0.0f, 0.0f, 0.0f);
		sc.setTarget(0.0f, 0.0f, 0.0f);
		sc.setTargetYAxis(0.0f, 1.0f, 0.0f);
		
		sc.setXAxis(1.0f, 0.0f, 0.0f);
		sc.setYAxis(0.0f, 1.0f, 0.0f);
		sc.setZAxis(0.0f, 0.0f, 1.0f);
		sc.setViewDir(0.0f, 0.0f, -1.0f);
		
		sc.setVelocity(0.0f, 0.0f, 0.0f);
		
		sc.viewMatrixIdentity();
		sc.projMatrixIdentity();
		sc.orientationIdentity();
	}
}