/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.CalculatorTest;

import edu.eci.pdsw.util.Pair;
import edu.eci.pdsw.commandpattern_testing.CalculadoraTarifas;
import edu.eci.pdsw.commandpattern_testing.ExcepcionParametrosInvalidos;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

public class CalculatorTest {

    private DateTime now;
    private float tarifa;

    @Before
    public void init() {
        now = new DateTime();
        tarifa = 1000f;
    }
    
    /**
     * Pruebas clase de equivalencia 1: FAILURE
    */
    @Test
    public void testClaseEquivalenciaUno() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(18,65).describedAs(e -> "Edad = " + e)
                   ,range(21,365).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa * (1 - 0.15f));
    }
    
    /**
     * Pruebas clase de equivalencia 2: PASS
    */
    @Test
    public void testClaseEquivalenciaDos() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(18,65).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa);
    }
    
    /**
     * Pruebas clase de equivalencia 3: PASS (esta es la prueba inicial)
    */
    @Test
    public void testClaseEquivalenciaTres() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(0,17).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa * (1 - 0.05));
    }
    
   
    
 
    /**
     * Pruebas clase de equivalencia 4: PASS
    */
    @Test
    public void testClaseEquivalenciaCuatro() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(0,17).describedAs(e -> "Edad = " + e)
                   ,range(21,365).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa*(1f-0.15)*(1f-0.05));
    }
    
    /**
     * Pruebas clase de equivalencia 5: PASS
    */
    @Test
    public void testClaseEquivalenciaCinco() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(66,125).describedAs(e -> "Edad = " + e)
                   ,range(21,365).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa*(1f-0.15f)*(1f-0.08));
    }
    
    /**
     * Pruebas clase de equivalencia 6: PASS
    */
    @Test
    public void testClaseEquivalenciaSeis() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(66,125).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minusDays(days),edad) == tarifa*(1f-0.08));
    }
    
}
