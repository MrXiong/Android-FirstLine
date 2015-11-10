package com.oneline.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name); // 写出name
		dest.writeInt(age);
	}
	Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

		@Override
		public Person createFromParcel(Parcel source) {
			Person person = new Person();
			//读取的顺序一定要和刚才写出的顺序完全相同
			person.name = source.readString();
			person.age = source.readInt();
			return person;
		}

		@Override
		public Person[] newArray(int size) {
			return new Person[size];
		}
	};
}
