import React, { Component } from 'react';

import {StyleSheet, FlatList, Text, View, Alert, TouchableOpacity, TextInput, Image,ToolbarAndroid} from 'react-native';
import DateTimePicker from "react-native-modal-datetime-picker";
import ToastExample from '../ToastExample';

export default class List extends Component {

    static navigationOptions = {
        title: 'Priome',
        headerTintColor: "white",
        headerStyle: {
            backgroundColor:"#008577"
        },
        headerTitleStyle: {
            fontSize: 18
        }
    };

    constructor(props) {
        super(props);
        this.state = {
            isDateTimePickerVisible: false,
            timePickerModeAndroid:'spinner',
        };
        this.state = {
            item: '',
            index:0,
            arraydays:[],
        };
        this.array = [],
            this.state = {arrayHolder: [], textInput_Holder: ''}

        ToastExample.returnArrayOfObjects(array => {
            this.state.arraydays=array;
            console.log(array, "The array you sent from the native side");
            for(let i=0;i<array.length;i++){
                {this.array.push({title :array[i].date +" "+ array[i].month+" "+array[i].hour + ':'+ array[i].minute+"          "+array[i].id});this.setState({ arrayHolder: [...this.array] })}
            }
        });
    }


    showNewAlarm = () => {
        /*this.setState({ isDateTimePickerVisible: true });*/
        this.props.navigation.replace('NewAlarm')
    };

    hideDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: false });
    };

    ListClick (index,item) {
        let dayss={ 1:0, 2:0 , 3:0 , 4:0 , 5:0, 6:0, 0:0 };
        dayss[0]=this.state.arraydays[index].Sun;
        dayss[1]=this.state.arraydays[index].Mon;
        dayss[2]=this.state.arraydays[index].Tue;
        dayss[3]=this.state.arraydays[index].Wed;
        dayss[4]=this.state.arraydays[index].Th;
        dayss[5]=this.state.arraydays[index].Fr;
        dayss[6]=this.state.arraydays[index].Sat;
        //ToastExample.show(dayss["1"].toString()+"sunil"+dayss["0"].toString()+this.state.arraydays[index].Sun,ToastExample.SHORT);
        this.props.navigation.replace('EditDelete',{'item': item,'dayss':dayss,})
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
                    renderItem={({ item,index }) => <Text style={styles.item}   onPress={this.ListClick.bind(this, index,item)} > {item.title} </Text>
                    }
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.showNewAlarm} style={styles.TouchableOpacityStyle} >

                    <Image source={{uri : 'https://reactnativecode.com/wp-content/uploads/2017/11/Floating_Button.png'}}

                           style={styles.FloatingButtonStyle} />

                </TouchableOpacity>

            </View>
    );
    }

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

