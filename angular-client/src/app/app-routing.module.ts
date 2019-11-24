import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddEntityComponent } from './add-entity/add-entity.component';
import { EntityComponent } from './entity/entity.component';
import { EditEntityComponent } from './entity/edit-entity/edit-entity.component';


const routes: Routes = [
    { path: 'addEntity', component: AddEntityComponent },
    { path: 'entities', component: EntityComponent },
    { path: 'entities/:id/edit', component: EditEntityComponent }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
