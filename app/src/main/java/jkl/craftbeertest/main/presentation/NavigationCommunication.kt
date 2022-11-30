package jkl.craftbeertest.main.presentation

interface NavigationCommunication {

    interface Observe: Communication.Observe<NavigationStrategy>

    interface Mutate : Communication.Mutate<NavigationStrategy>

    interface Mutable : Observe, Mutate

    class Base : Communication.SingleUi<NavigationStrategy>(), Mutable
}