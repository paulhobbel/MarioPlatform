package org.jbox2d.callbacks;

import org.jbox2d.collision.AABB;
import org.jbox2d.dynamics.World;

/**
 * Callback class for AABB queries. See
 * {@link World#queryAABB(QueryCallback, AABB)}.
 * 
 * @author dmurph
 * 
 */
public interface ParticleQueryCallback {
  /**
   * Called for each particle found in the query AABB.
   * 
   * @return false to terminate the query.
   */
  boolean reportParticle(int index);
}
