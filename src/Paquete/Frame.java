package Paquete;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lazarod
 */
public final class Frame extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     */
    
    /*  PROFE EL TXT DE PALABRAS SE DEBE LLAMAR "palabras" Y DEBE ESTAR EN LA CARPETA DEL PROYECTO 
    */
    File archivo;
    Nodo_Arbol raiz;
    int t = 0;
    public Frame() {
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.PINK);
        initComponents();
        File file = new File("palabras.txt");
        archivo = file;
        if (raiz == null){
            Nodo_Arbol p = new Nodo_Arbol("RAIZ");
            raiz = p;
        }
        try{
            Scanner scan = new Scanner(archivo);
            while(scan.hasNext()){
                String linea = scan.nextLine();
                añadir(raiz,linea);
            }
        }catch(FileNotFoundException ex){}
    }
    
    public void añadir(Nodo_Arbol raiz, String linea){
        if(raiz.vector[0] == null){
            llenar(linea,raiz, 0);
        }
        else{
            meter(raiz,linea,0);
        }
    }
    
    public void meter(Nodo_Arbol p,String linea, int inicio){
        int i = 0,sw = 0, pos = 0;
        while((inicio < linea.length() && sw == 0) && (i < p.tam  && sw == 0)){
            if (p.vector[i].letra.equals(substr(linea,inicio,1))){
                sw = 1;
                pos = i;
            }
            else{
                i = i + 1;
            }
        }
        if (sw == 1){
            meter(p.vector[pos],linea,inicio + 1);
        }
        else{
            llenar(linea,p,inicio);
        }
    }
       
    public void llenar(String linea,Nodo_Arbol n,int inicio){
        int cont = 0;
        Nodo_Arbol k = n;
        while(inicio < linea.length()){
            Nodo_Arbol q = new Nodo_Arbol(substr(linea,inicio,1));
            if(cont == 0){
                k.vector[k.tam] = q;
                cont = cont + 1;
            } 
            else{
                k = k.vector[k.tam - 1];
                k.vector[k.tam] = q;
            }
            inicio = inicio + 1;
            k.tam = k.tam + 1;
        }
    }
    
    Nodo_Arbol buscar(Nodo_Arbol p, String palabra, int inicio){
        int i = 0, cont = 0, w;
        Nodo_Arbol k = null;
        if(inicio < palabra.length()){
            for(int j = 0; j < p.tam;j++){
                if (p.vector[j].letra.equals(substr(palabra,inicio,1))){
                    i = j;
                    cont = cont + 1;
                }
            }
            if (cont == 0){
                return null;
            }
            else{
                p = buscar(p.vector[i],palabra,inicio + 1);
            }
        }
        return p;
    }
    
    public void sugerencias(Nodo_Arbol p, String palabra, String[] y){
        String f = palabra;
        int i = 0, sw = 0, sw2 = 0, j = 0;
        if (p != null){
            if (p.vector[0] != null){
                while(i < p.tam){
                    f = palabra;
                    try{
                        Scanner scan = new Scanner(archivo);
                        while(scan.hasNext() && sw == 0){
                            String linea = scan.nextLine();
                            if (linea.equals(f)){
                                while(j < t && sw2 == 0){
                                    if(y[j].equals(f)){
                                        sw2 = 1;
                                    }
                                    j = j + 1;
                                }
                                if (sw2 == 0){
                                    y[t]= f;
                                    t = t + 1;
                                }
                            }
                        }
                    }catch(FileNotFoundException ex){}
                    f = f + p.vector[i].letra;
                    sugerencias(p.vector[i],f, y);
                    i = i + 1;
                }
            }
            else{
                j = 0;
                sw2 = 0;
                while(j < t && sw2 == 0){
                    if(y[j].equals(f)){
                        sw2 = 1;
                    }
                    j = j + 1;
                }
                if (sw2 == 0){
                    y[t]= f;
                    t = t + 1;
                }
            }
        }
    }
    
    String substr(String cad, int ini, int lon){
        return  cad.substring(ini, ini+lon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        palabra = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Plantagenet Cherokee", 3, 18)); // NOI18N
        jLabel1.setText("Palabra:");

        palabra.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        palabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                palabraActionPerformed(evt);
            }
        });
        palabra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                palabraKeyReleased(evt);
            }
        });

        tabla.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sugerencias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(palabra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(palabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void palabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_palabraActionPerformed

    }//GEN-LAST:event_palabraActionPerformed

    private void palabraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_palabraKeyReleased
        t = 0;
        Nodo_Arbol b = buscar(raiz,palabra.getText(), 0);
        String [] w = new String [500];
        sugerencias(b,palabra.getText(), w);
        String p = palabra.getText();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int filas = model.getRowCount();
        for (int i = 0; i < filas; i++) {
           model.removeRow(0);
        }
        tabla.setModel(model);
        if (p.length() > 0){
            Object[] fila = new Object[model.getColumnCount()];
            if (w[0] != null){
                for(int i = 0; i < t; i++){
                    fila[0] = w[i];
                    model.addRow(fila);
                }
            }
            else{
                String error;
                error = substr(p,0,p.length()- 1);
                if (error.equals("") == false){
                    b = buscar(raiz,error, 0);
                    sugerencias(b,error, w);
                    for(int i = 0; i < t; i++){
                        fila[0] = w[i];
                        model.addRow(fila);
                    }
                }else{
                    System.out.println("");
                }
            }
            tabla.setModel(model);
        }
    }//GEN-LAST:event_palabraKeyReleased
    
    String izq(String cad, int lon){
        return cad.substring(0, lon);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField palabra;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
