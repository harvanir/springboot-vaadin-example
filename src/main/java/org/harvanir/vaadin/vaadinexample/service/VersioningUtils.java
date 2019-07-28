package org.harvanir.vaadin.vaadinexample.service;

import java.io.Serializable;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.hibernate.pretty.MessageHelper;

/**
 * @author Harvan Irsyadi
 */
public class VersioningUtils {

  private VersioningUtils() {
  }

  public static void checkVersion(Long requestVersion, Long dbVersion, Object objectDb,
      Serializable dbId) {
    if (!requestVersion.equals(dbVersion)) {
      throw new OptimisticEntityLockException(
          objectDb,
          "Newer version [" + dbVersion +
              "] of entity [" + MessageHelper.infoString(objectDb.getClass().getSimpleName(), dbId)
              + "] found in database"
      );
    }
  }
}