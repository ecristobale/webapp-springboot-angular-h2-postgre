import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AddEntityComponent } from './add-entity/add-entity.component';
import { EntityComponent } from './entity/entity.component';
import { HttpClientModule } from '@angular/common/http';
import { EditEntityComponent } from './entity/edit-entity/edit-entity.component';

@NgModule({
  declarations: [
    AppComponent,
    AddEntityComponent,
    EntityComponent,
    EditEntityComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
