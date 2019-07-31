import React, { Component } from 'react';

import {StyleSheet, FlatList, Text, View, Alert, TouchableOpacity, TextInput, Image,ToolbarAndroid} from 'react-native';
import DateTimePicker from "react-native-modal-datetime-picker";
import ToastExample from '../ToastExample';

export default class List extends Component {

    static navigationOptions = {
        title: 'Priome',
        headerTintColor: "white",
        headerStyle: {
            backgroundColor:"blue"
        },
        headerTitleStyle: {
            fontSize: 18
        }
    };

    constructor(props) {
        super(props);
        this.state = {
            isDateTimePickerVisible: false
        };
        this.state = {
            item: '',
        };
        this.array = [],
            this.state = {arrayHolder: [], textInput_Holder: ''}

        ToastExample.returnArrayOfObjects(array => {
            console.log(array, "The array you sent from the native side");
            for(let i=0;i<array.length;i++){
                {this.array.push({title : array[i].hour + ':' +array[i].minute+' ' +array[i].id});this.setState({ arrayHolder: [...this.array] })}
            }
        });
    }

    SampleFunction=()=>{

        Alert.alert("Floating Button Clicked");

    }


    showDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: true });
    };

    hideDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: false });
    };
    handleDatePicked = date => {
        console.log("A date has been picked: ", date);
       // ToastExample.show(date.getDay()+""+date.getMonth()+date.getHours()+""+date.getMinutes(),ToastExample.SHORT);
        this.hideDateTimePicker();
        var dat = new Date();
        var month=dat.getMonth()+1;
        dat=date;
        this.hideDateTimePicker();
        ToastExample.saveAlarm(dat.getHours(),dat.getMinutes(),0);
        this.array=[]
        this.setState({ arrayHolder: [...this.array] })
        ToastExample.returnArrayOfObjects(array => {
            console.log(array, "The array you sent from the native side");
            for(let i=0;i<array.length;i++){
                {this.array.push({title :array[i].hour + ':'+ array[i].minute+" "+array[i].id});this.setState({ arrayHolder: [...this.array] })}
          //  ToastExample.show("sunil"+array[i].hour,ToastExample.SHORT);
            }
        });
    };


    render() {
        return (
            <View style={styles.MainContainers}>
                <FlatList
                    data={this.state.arrayHolder}
                    width='100%'
                    extraData={this.state.arrayHolder}
                    keyExtractor={(index) => index.toString()}
                    ItemSeparatorComponent={this.FlatListItemSeparator}
                    renderItem={({ item }) => <Text style={styles.item}   onPress={() =>
                        this.props.navigation.replace('EditDelete',{'item': item,})} > {item.title} </Text>
                    }
                />
                <DateTimePicker
                    mode='time'
                    isVisible={this.state.isDateTimePickerVisible}
                    onConfirm= {this.handleDatePicked}
                    onCancel={this.hideDateTimePicker}
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.showDateTimePicker} style={styles.TouchableOpacityStyle} >

                    <Image source={{uri : 'https://reactnativecode.com/wp-content/uploads/2017/11/Floating_Button.png'}}

                           style={styles.FloatingButtonStyle} />

                </TouchableOpacity>

            </View>
    );
    }



    componentDidMount() {this.setState({ arrayHolder: [...this.array] })}


    joinData = () => {this.array.push({title : this.state.textInput_Holder});this.setState({ arrayHolder: [...this.array] })}

    FlatListItemSeparator = () => {
        return (
            <View
                style={{
                    height: 1,
                    width: "100%",
                    backgroundColor: "#607D8B",
                }}
            />
        );
    }

    GetItem(item) {Alert.alert(item);}
}

const styles = StyleSheet.create({
    toolbar: {
        backgroundColor: '#2196F3',
        height: 56,
        alignSelf: 'stretch',
        textAlign: 'center',
    },
    MainContainers: {
        justifyContent: 'center',
        alignItems: 'center',
        flex: 1,
    },

    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },

    textInputStyle: {
        textAlign: 'center',
        height: 40,
        width: '90%',
        borderWidth: 1,
        borderColor: '#4CAF50',
        borderRadius: 7,
        marginTop: 12
    },

    button: {
        width: '90%',
        height: 40,
        padding: 10,
        backgroundColor: '#4CAF50',
        borderRadius: 8,
        marginTop: 10
    },

    buttonText: {
        color: '#fff',
        textAlign: 'center',
    },
    MainContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor : '#F5F5F5'
    },

    TouchableOpacityStyle:{

        position: 'absolute',
        width: 50,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        right: 30,
        bottom: 30,
    },

    FloatingButtonStyle: {
        resizeMode: 'contain',
        width: 50,
        height: 50,
    }

});

