/**
 * Created by Uzytkownik on 09.11.2016.
 */
var placePawn= function(fields, field){
    var FieldEnum = Java.type('game.FieldEnum')
    print('Hi there from Javascript, \n');
    print(fields.toString())
    print('Hi there from Javascript, \n');
    print(field.toString())
    fields.stream().filter(function(f) {
        return( ((f.getX() >= field.getX() && f.getX() < field.getX()+4) &&
        f.getY() >= field.getY() && f.getY() < field.getY()+4)
            &&f.getFieldEnum().equals(FieldEnum.FREE));
    }).forEach( function(f) { f.setFieldEnum(FieldEnum.COVERED)
    });

}
