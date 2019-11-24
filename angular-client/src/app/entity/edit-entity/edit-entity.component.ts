import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/app/entity.model';
import { EntityService } from 'src/app/entity.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-edit-entity',
  templateUrl: './edit-entity.component.html',
  styleUrls: ['./edit-entity.component.css']
})
export class EditEntityComponent implements OnInit {
  entity = new Entity();
  sub: Subscription;

  constructor(private entityService: EntityService,
              private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.getEntity(this.route.snapshot.params.id);
  }

  onUpdateEntity() {
    console.log(this.entity);
    this.entityService.updateEntity(this.entity)
    .subscribe(res => {
        this.router.navigate(['/entities']);
      }, (err) => {
        console.log(err);
      }
    );
  }

  getEntity(id) {
    this.entityService.getEntity(id).subscribe(data => {
      console.log(data);
      this.entity.id = data.id;
      this.entity.name = data.name;
      this.entity.description = data.description;
    });
  }

}
