package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTP1;
  private TorpedoStore mockTP2;

  @BeforeEach
  public void init(){
    mockTP1 = mock(TorpedoStore.class);
    mockTP2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTP1, mockTP2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTP1.fire(1)).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTP1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTP1.fire(1)).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTP1, times(1)).fire(1);
    verify(mockTP2, times(1)).fire(1);
  }

}
