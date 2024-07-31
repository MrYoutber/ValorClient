package valor.optimizations;

import javax.vecmath.Matrix4f;

import net.minecraft.util.AxisAlignedBB;

public class CustomFrustum {
	private final Plane[] planes = new Plane[6];

	public CustomFrustum(Matrix4f matrix) {
		// Extract the planes from the matrix
		this.planes[0] = new Plane(matrix.m30 + matrix.m00, matrix.m31 + matrix.m01, matrix.m32 + matrix.m02,
				matrix.m33 + matrix.m03); // Left
		this.planes[1] = new Plane(matrix.m30 - matrix.m00, matrix.m31 - matrix.m01, matrix.m32 - matrix.m02,
				matrix.m33 - matrix.m03); // Right
		this.planes[2] = new Plane(matrix.m30 + matrix.m10, matrix.m31 + matrix.m11, matrix.m32 + matrix.m12,
				matrix.m33 + matrix.m13); // Bottom
		this.planes[3] = new Plane(matrix.m30 - matrix.m10, matrix.m31 - matrix.m11, matrix.m32 - matrix.m12,
				matrix.m33 - matrix.m13); // Top
		this.planes[4] = new Plane(matrix.m30 + matrix.m20, matrix.m31 + matrix.m21, matrix.m32 + matrix.m22,
				matrix.m33 + matrix.m23); // Near
		this.planes[5] = new Plane(matrix.m30 - matrix.m20, matrix.m31 - matrix.m21, matrix.m32 - matrix.m22,
				matrix.m33 - matrix.m23); // Far
	}

	public boolean isBoundingBoxInFrustum(AxisAlignedBB box) {
		for (Plane plane : planes) {
			if (!plane.isBoxInPlane(box)) {
				return false;
			}
		}
		return true;
	}

	private static class Plane {
		private final float a, b, c, d;

		public Plane(float a, float b, float c, float d) {
			float length = (float) Math.sqrt(a * a + b * b + c * c);
			this.a = a / length;
			this.b = b / length;
			this.c = c / length;
			this.d = d / length;
		}

		public boolean isBoxInPlane(AxisAlignedBB box) {
			float x1 = (float) box.minX, x2 = (float) box.maxX;
			float y1 = (float) box.minY, y2 = (float) box.maxY;
			float z1 = (float) box.minZ, z2 = (float) box.maxZ;

			return a * x1 + b * y1 + c * z1 + d > 0 || a * x2 + b * y1 + c * z1 + d > 0
					|| a * x1 + b * y2 + c * z1 + d > 0 || a * x2 + b * y2 + c * z1 + d > 0
					|| a * x1 + b * y1 + c * z2 + d > 0 || a * x2 + b * y1 + c * z2 + d > 0
					|| a * x1 + b * y2 + c * z2 + d > 0 || a * x2 + b * y2 + c * z2 + d > 0;
		}
	}
}
