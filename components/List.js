import React, { Component } from 'react';

import {StyleSheet, FlatList, Text, View, Alert, TouchableOpacity, TextInput, Image} from 'react-native';
import DateTimePicker from "react-native-modal-datetime-picker";

export default class Myproject extends Component {


    constructor(props) {
        super(props);
        this.state = {
            isDateTimePickerVisible: false
        };
        this.array = [],
            this.state = {arrayHolder: [], textInput_Holder: ''}
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
        this.hideDateTimePicker();
        {data => this.setState({ textInput_Holder: date })}
        {this.joinData}
    };


    render() {
        return (
            <View style={styles.MainContainers}>

                <TextInput
                    placeholder="Enter Value Here"
                    onChangeText={data => this.setState({ textInput_Holder: data })}
                    style={styles.textInputStyle}
                    underlineColorAndroid='transparent'
                />

                <TouchableOpacity onPress={this.joinData} activeOpacity={0.7} style={styles.button} >

                    <Text style={styles.buttonText}> Add Values To FlatList </Text>

                </TouchableOpacity>

                <FlatList
                    data={this.state.arrayHolder}
                    width='100%'
                    extraData={this.state.arrayHolder}
                    keyExtractor={(index) => index.toString()}
                    ItemSeparatorComponent={this.FlatListItemSeparator}
                    renderItem={({ item }) => <Text style={styles.item} onPress={this.GetItem.bind(this, item.title)} > {item.title} </Text>}
                />
                <DateTimePicker
                    mode='time'
                    isVisible={this.state.isDateTimePickerVisible}
                    onConfirm={this.handleDatePicked}
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

    MainContainers: {
        justifyContent: 'center',
        alignItems: 'center',
        flex: 1,
        margin: 2

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

