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

  @Test
  public void firetorpedo_Single_Alternate_Success() {
    // Arrange
    when(mockTP1.fire(1)).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTP1, times(1)).fire(1);
    verify(mockTP2, times(1)).fire(1);
  }

  @Test
  public void firetorpedo_Single_Next_Empty_Success() {
    // Arrange
    when(mockTP1.isEmpty()).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTP1, times(0)).fire(1);
    verify(mockTP2, times(1)).fire(1);
  }

  @Test
  public void firetorpedo_Single_First_Shots_Success() {
    // Arrange
    when(mockTP1.isEmpty()).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTP1, times(0)).fire(1);
    verify(mockTP2, times(1)).fire(1);
  }

  @Test
  public void firetorpedo_Single_AllStore_Empty_Success() {
    // Arrange
    when(mockTP1.isEmpty()).thenReturn(true);
    when(mockTP2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTP1, times(0)).fire(1);
    verify(mockTP2, times(0)).fire(1);
  }

  @Test
  public void firetorpedo_All_AllStore_Empty_Success() {
    // Arrange
    when(mockTP1.isEmpty()).thenReturn(true);
    when(mockTP2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockTP1, times(0)).fire(1);
    verify(mockTP2, times(0)).fire(1);
  }

  @Test
  public void firetorpedo_Single_Primary_fired_Secondary_Empty_Success() {
    // Arrange
    when(mockTP1.fire(1)).thenReturn(true);
    when(mockTP2.isEmpty()).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTP1, times(2)).fire(1);
    verify(mockTP2, times(0)).fire(1);
  }

  @Test
  public void firetorpedo_None_Firingmode_Success() {

    // Act
    boolean result = ship.fireTorpedo(FiringMode.NONE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void firetorpedo_Single_Primary_fired_Secondary_Empty_Primary_Empty_Success() {
    // Arrange
    when(mockTP1.fire(1)).thenReturn(true);
    when(mockTP2.isEmpty()).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    when(mockTP1.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTP1, times(1)).fire(1);
    verify(mockTP2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Primary_Empty_Success(){
    // Arrange
    when(mockTP1.isEmpty()).thenReturn(true);
    when(mockTP2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTP1, times(0)).fire(1);
    verify(mockTP2, times(1)).fire(1);
  }

}
