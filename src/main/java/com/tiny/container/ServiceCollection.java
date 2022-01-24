package com.tiny.container;

public class ServiceCollection implements IServiceCollection {
    private final TinyContainer container;

    public TinyContainer getContainer() {
        return container;
    }

    public ServiceCollection() {
        container = new TinyContainer();

    }

    @Override
    public <TSource> void addTransient(Class<TSource> source, Class<? extends TSource> target) {
        container.addTransient(source, target);
    }

    @Override
    public <TSource> void addSingleton(Class<TSource> source, Class<? extends TSource> target) {
        container.addSingleton(source, target);
    }

    public <T> T resolve(Class<T> source) {
        return container.resolve(source);
    }

    @Override
    public <TSource> void AddRequestScope(Class<TSource> source, Class<? extends TSource> target) {
        container.addScope(source, target);
    }

    @Override
    public <TSource> void register(Class<TSource> source, Scope scope) {
        container.register(new Descriptor(source,source,scope));
    }
}